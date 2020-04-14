package main.items.Board.Service;

import main.items.Board.json.BoardEssentialsView;
import main.items.Board.json.BoardView;
import main.items.Person.json.PersonEssentialDataView;

import java.util.List;

public interface BoardService {

    Long createNewBoard(BoardView boardView);

    Long updateBoard(BoardView boardView, Long id);

    BoardView getBoardData(Long id);

    List<BoardEssentialsView> getUsersBoards(Long id);

    List<PersonEssentialDataView> getBoarddUsers(Long id);
}
