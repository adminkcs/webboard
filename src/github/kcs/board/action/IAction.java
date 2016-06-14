package github.kcs.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;

public interface IAction {

    String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}