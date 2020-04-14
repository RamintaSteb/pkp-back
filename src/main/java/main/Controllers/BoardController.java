package main.Controllers;

import main.items.Board.Service.BoardService;
import main.items.Board.json.BoardEssentialsView;
import main.items.Board.json.BoardView;
import main.items.Person.json.PersonEssentialDataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/createBoard")
    public Long addNewBoard(@RequestBody BoardView boardView) {
        return boardService.createNewBoard(boardView);
    }

    @PostMapping("/updateBoard")
    public Long updateBoard(@RequestBody BoardView boardView, @RequestBody Long id) {
        return boardService.updateBoard(boardView, id);
    }

    @GetMapping("/board")
    public @ResponseBody BoardView getBoard(@RequestParam Long id) {
        return boardService.getBoardData(id);
    }

    @GetMapping("/AllUserBoards")
    public @ResponseBody
    List<BoardEssentialsView> getBoardsAssignedToUser(@RequestParam Long id) {
        return boardService.getUsersBoards(id);
    }

    @GetMapping("/getBoardUsersEssentialData")
    public List<PersonEssentialDataView> getPersonsEssentialsList(@RequestParam Long id) {
        return boardService.getBoarddUsers(id);
    }
}