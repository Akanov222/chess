package edu.iv.javacourse.servlet;

import edu.iv.javacourse.board.*;
import edu.iv.javacourse.board.fen.FenService;
import edu.iv.javacourse.event.GameEventPublisher;
import edu.iv.javacourse.event.listener.GameEventListener;
import edu.iv.javacourse.event.listener.GameHistoryListener;
import edu.iv.javacourse.game.ChessGameService;
import edu.iv.javacourse.game.GameRegistry;
import edu.iv.javacourse.game.GameState;
import edu.iv.javacourse.move.Move;
import edu.iv.javacourse.move.MoveResult;
import edu.iv.javacourse.view.BoardHtmlRenderer;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;

import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/game")
public class ChessServlet extends HttpServlet {

    private ChessGameService chessGameService;
    private FenService fenService;
    private BoardHtmlRenderer renderer;
    private TemplateEngine templateEngine;
    private GameRegistry gameRegistry;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // 1. Событийная инфраструктура
        GameEventPublisher publisher = new GameEventPublisher();
        publisher.addListener(new GameHistoryListener());

        // 2. Сервисы
        this.fenService = new FenService(HashMapBoard::new);
        this.chessGameService = new ChessGameService(publisher);
        this.renderer = new BoardHtmlRenderer();
        this.gameRegistry = new GameRegistry();

        // 3. Стартовая партия + событие onGameStarted
        GameState gameState = fenService.createDefaultGame();
        publisher.publishGameStarted();

        // 4. Thymeleaf
        var jakartaApplication = JakartaServletWebApplication.buildApplication(getServletContext());
        var resolver = new WebApplicationTemplateResolver(jakartaApplication);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GameState currentState;
        synchronized (lock) {
            currentState = this.gameState;
        }
        MDC.put("gameId", currentState.getGameId());

        try {
            var jakartaApplication = JakartaServletWebApplication.buildApplication(getServletContext());
            var exchange = jakartaApplication.buildExchange(request, response);
            var context = new WebContext(exchange);

            context.setVariable("boardRows", renderer.getBoardView(currentState.getBoard()));
            context.setVariable("turn", currentState.getTurn());
            context.setVariable("error", request.getSession().getAttribute("error"));
            request.getSession().removeAttribute("error"); // Чистим ошибку после показа

            response.setContentType("text/html;charset=UTF-8");
            templateEngine.process("chess-board", context, response.getWriter());
        } finally {
            MDC.remove("gameId");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fromString = request.getParameter("from");
        String toString = request.getParameter("to");

        GameState currentState;
        synchronized (lock) {
            currentState = this.gameState;
        }
        MDC.put("gameId", currentState.getGameId());

        try {
            Coordinates from = parseCoordinates(fromString);
            Coordinates to = parseCoordinates(toString);
            Move move = Move.normal(from, to);

            MoveResult result = chessGameService.makeMove(currentState, move);

            if (!result.isSuccess()) {
                request.getSession().setAttribute("error", result.getMessage());
            }
        } catch (Exception e) {
            log.warn("Failed to parse/apply move: from={}, to={}", fromString, toString, e);
            request.getSession().setAttribute("error", "Incorrect format coordinates");
        } finally {
            MDC.remove("gameId");
        }

        response.sendRedirect(request.getContextPath() + "/game");
    }

    private Coordinates parseCoordinates(String inputStringCoordinates) {
        inputStringCoordinates = inputStringCoordinates.trim().toLowerCase();
        File file = File.valueOf(inputStringCoordinates.substring(0, 1).toUpperCase());
        int rank = Integer.parseInt(inputStringCoordinates.substring(1));
        return new Coordinates((file), rank);
    }
}
