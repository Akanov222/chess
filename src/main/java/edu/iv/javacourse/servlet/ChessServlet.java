package edu.iv.javacourse.servlet;

import edu.iv.javacourse.Game;
import edu.iv.javacourse.board.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;

import java.io.IOException;

@WebServlet(urlPatterns = "/game")
public class ChessServlet extends HttpServlet {

    private Game game;
    private Board board;
    private BoardHtmlRenderer renderer;
    private TemplateEngine templateEngine;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.board = new HashMapBoard();
        new BoardFactory().setupDefaultPiecesPositions(board);
        this.renderer = new BoardHtmlRenderer();
        this.game = new Game(board, null);

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
        var jakartaApplication = JakartaServletWebApplication.buildApplication(getServletContext());
        var exchange = jakartaApplication.buildExchange(request, response);
        var context = new WebContext(exchange);

        context.setVariables("boardRows", renderer.getBoardView(board));
        context.setVariables("turn", game.getColorToMove());
        context.setVariable("error", request.getSession().getAttribute("error"));
        request.getSession().removeAttribute("error"); // Чистим после показа
        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("chess-board", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromString = request.getParameter("from");
        String toString = request.getParameter("to");

        try {
            Coordinates fromCoordinates = parsCoordinates(fromString);
            Coordinates toCoordinates = parsCoordinates(toString);
            boolean success = game.makeMove(fromCoordinates, toCoordinates);

            if (!success) {
                request.getSession().setAttribute("error", "Incorrect move");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Incorrect format coordinates");
        }
        response.sendRedirect(request.getContextPath() + "/game");
    }

    private Coordinates parsCoordinates(String inputStringCoordinates) {
        inputStringCoordinates = inputStringCoordinates.trim().toLowerCase();
        File file = File.valueOf(inputStringCoordinates.substring(0, 1).toUpperCase());
        int rank = Integer.parseInt(inputStringCoordinates.substring(1));
        return new Coordinates((file), rank);
    }
}
