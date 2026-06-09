package edu.iv.javacourse.servlet;

import edu.iv.javacourse.Game;
import edu.iv.javacourse.board.*;
import edu.iv.javacourse.board.fen.FenService;
import edu.iv.javacourse.board.fen.GameState;
import edu.iv.javacourse.event.GameHistoryListener;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;

import java.io.IOException;
import java.util.function.Supplier;

@WebServlet(urlPatterns = "/game")
public class ChessServlet extends HttpServlet {
    private final String gameId = "";
//    private Game game;
    private GameState gameState;
    private Board board;
    private BoardHtmlRenderer renderer;
    private TemplateEngine templateEngine;

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        this.board = new HashMapBoard();
        FenService fenService = new FenService((Supplier<Board>) board);
        gameState = fenService.createDefaultGame();
        this.renderer = new BoardHtmlRenderer();
//        this.game = new Game(board);
//        this.game.addListener(new GameHistoryListener());

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
        MDC.put("gameId", gameId);
        var jakartaApplication = JakartaServletWebApplication.buildApplication(getServletContext());
        var exchange = jakartaApplication.buildExchange(request, response);
        var context = new WebContext(exchange);

        context.setVariable("boardRows", renderer.getBoardView(board));
        context.setVariable("turn", game.getColorToMove());
        context.setVariable("error", request.getSession().getAttribute("error"));
        request.getSession().removeAttribute("error"); // Чистим после показа
        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("chess-board", context, response.getWriter());
        MDC.remove("gameId");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MDC.put("gameId", gameId);
        String fromString = request.getParameter("from");
        String toString = request.getParameter("to");

        try {
            Coordinates fromCoordinates = parseCoordinates(fromString);
            Coordinates toCoordinates = parseCoordinates(toString);
            boolean success = game.makeMove(fromCoordinates, toCoordinates);

            if (!success) {
                request.getSession().setAttribute("error", "Incorrect move");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Incorrect format coordinates");
        }
        response.sendRedirect(request.getContextPath() + "/game");
        MDC.remove("gameId");
    }

    private Coordinates parseCoordinates(String inputStringCoordinates) {
        inputStringCoordinates = inputStringCoordinates.trim().toLowerCase();
        File file = File.valueOf(inputStringCoordinates.substring(0, 1).toUpperCase());
        int rank = Integer.parseInt(inputStringCoordinates.substring(1));
        return new Coordinates((file), rank);
    }
}
