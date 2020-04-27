package main.items.Board.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.Board.Entity.Board;
import main.items.Board.Repo.BoardRepo;
import main.items.Board.Service.BoardService;
import main.items.Board.json.BoardEssentialsView;
import main.items.Board.json.BoardView;
import main.items.Person.Entity.Person;
import main.items.Person.Repo.PersonRepo;
import main.items.Person.json.PersonEssentialDataView;
import main.items.Task.Entity.Task;
import main.items.Task.Repo.TaskRepo;
import main.items.Task.json.TaskDataView;
import main.items.Task.json.TaskInformationView;
import main.items.WorkTime.json.WorkTimeDataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepo boardRepo;

    @Autowired
    private PersonRepo personRepo;

    @Override
    public Long createNewBoard(BoardView boardView) {
        return boardRepo.save(buildBoard(boardView)).getId();
    }

    @Override
    public Long updateBoard(BoardView boardView, Long id) {
        Board board = boardRepo.findById(id).orElse(null);
        if (board == null) {
            throw new IllegalArgumentException();
        }

        board.toBuilder()
                .title(boardView.getTitle())
                .description(boardView.getDescription())
                .assignedUsers(boardView.getAssignedUsers() == null ? null : personRepo.findAllById(boardView.getAssignedUsers()))
                .build();
         return boardRepo.save(board).getId();
    }

    @Override
    public List<BoardEssentialsView> getUsersBoards(Long id) {
        Person person = personRepo.findById(id).orElse(null);
        if (person == null) {
            return null;
        }
        return buildBoardEssentialsViews(person.getBoards());
    }

    @Override
    public List<PersonEssentialDataView> getBoarddUsers(Long id) {
        List<PersonEssentialDataView> personsList = new ArrayList<PersonEssentialDataView>();
        Board board = boardRepo.findById(id).orElse(null);

        if (board != null) {
            for (Person person : board.getAssignedUsers()) {
                personsList.add(PersonEssentialDataView.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .surname(person.getSurname())
                        .build());
            }
        }

        return personsList;
    }

    @Override
    public BoardView getBoardData(Long id) {
        return buildBoardView(boardRepo.findById(id).orElse(null));
    }

    private List<BoardEssentialsView> buildBoardEssentialsViews(List<Board> boards) {
        List<BoardEssentialsView> boardEssentialsViewList = new ArrayList<>();
        boards.forEach(board -> {
            boardEssentialsViewList.add(buildBoardEssentialsView(board));
        });
        return boardEssentialsViewList;
    }

    private BoardEssentialsView buildBoardEssentialsView(Board board) {
        return board == null ? null : BoardEssentialsView.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .numberOfMembers(board.getAssignedUsers().size())
                .build();
    }

    private BoardView buildBoardView(Board board) {
        return board == null ? null : BoardView.builder()
                .title(board.getTitle())
                .description(board.getDescription())
                .assignedUsers(builAssignedUsers(board.getAssignedUsers()))
                .taskData(builBoardsTasks(board.getTasks()))
                .build();
    }

    private List<TaskInformationView> builBoardsTasks(List<Task> tasksList) {
        List<TaskInformationView> taskssList = new ArrayList<>();
        tasksList.forEach(Task -> {
            taskssList.add(TaskInformationView.builder()
                    .id(Task.getId())
                    .title(Task.getTitle())
                    .estimatedTime(Task.getEstimatedTime())
                    .status(Task.getStatus())
                    .build());
        });
        return taskssList;
    }

    private List<Long> builAssignedUsers(List<Person> personList) {
        List<Long> idsList = new ArrayList<>();
        personList.forEach(person -> idsList.add(person.getId()));
        return idsList;
    }

    private Board buildBoard(BoardView boardView) {
        return Board.builder()
                .title(boardView.getTitle())
                .description(boardView.getDescription())
                .assignedUsers(boardView.getAssignedUsers() == null ? null : personRepo.findAllById(boardView.getAssignedUsers()))
                .tasks(null)
                .build();
    }
}
