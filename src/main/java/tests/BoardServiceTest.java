package tests;

import main.items.Board.Entity.Board;
import main.items.Board.Repo.BoardRepo;
import main.items.Board.Service.impl.BoardServiceImpl;
import main.items.Board.json.BoardEssentialsView;
import main.items.Board.json.BoardView;
import main.items.Person.Entity.Person;
import main.items.Person.Repo.PersonRepo;
import main.items.Person.json.PersonEssentialDataView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardServiceImpl boardService;
    @Mock
    private BoardRepo boardRepo;
    @Mock
    private PersonRepo personRepo;

    private Long id = 1L;
    private String description = "description";
    private String title = "title";
    private int numberOfMembers = 0;
    private String name = "name";
    private String surname = "surname";

    @Test
    void createNewBoard() {
        BoardView boardView = new BoardView();
        Board boardToReturnFromRepository = Board.builder()
                .id(id)
                .build();
        when(boardRepo.save(any(Board.class))).thenReturn(boardToReturnFromRepository);

        Long testId = boardService.createNewBoard(boardView);

        assertEquals(id, testId);
    }

    @Test
    void updateBoard() {
        BoardView boardView = BoardView.builder()
                .description(description)
                .build();
        Long boardId = 1L;
        Board boardToReturnFromRepository = Board.builder()
                .id(id)
                .build();
        when(boardRepo.save(any(Board.class))).thenReturn(boardToReturnFromRepository);

        Long testId = boardService.updateBoard(boardView, boardId);

        assertEquals(id, testId);
    }

    @Test
    void getUsersBoards() {
        Long personId = id;
        List<Board> boards = new ArrayList<>();
        boards.add(Board.builder()
                .id(id)
                .title(title)
                .description(description)
                .assignedUsers(new ArrayList<>())
                .build());
        Person personToReturnFromRepository = Person.builder()
                .boards(boards)
                .build();

        when(personRepo.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(personToReturnFromRepository));

        BoardEssentialsView boardEssentialsView = boardService.getUsersBoards(personId).get(0);

        assertEquals(id, boardEssentialsView.getId());
        assertEquals(title, boardEssentialsView.getTitle());
        assertEquals(description, boardEssentialsView.getDescription());
        assertEquals(numberOfMembers, boardEssentialsView.getNumberOfMembers());
    }

    @Test
    void getBoarddUsers() {
        Long boardId = id;
        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build());
        Board boardToReturnFromRepository = Board.builder()
                .assignedUsers(persons)
                .build();

        when(boardRepo.findById(any(Long.class)))
                .thenReturn(java.util.Optional.ofNullable(boardToReturnFromRepository));

        PersonEssentialDataView personEssentialDataView = boardService.getBoarddUsers(boardId).get(0);

        assertEquals(id, personEssentialDataView.getId());
        assertEquals(name, personEssentialDataView.getName());
        assertEquals(surname, personEssentialDataView.getSurname());
    }

    @Test
    void getBoardData() {
        Long boardId = id;
        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder()
                .id(id)
                .build());
        Board boardToReturnFromRepository = Board.builder()
                .title(title)
                .description(description)
                .assignedUsers(persons)
                .tasks(new ArrayList<>())
                .build();
        when(boardRepo.findById(any(Long.class)))
                .thenReturn(java.util.Optional.ofNullable(boardToReturnFromRepository));

        BoardView boardView = boardService.getBoardData(boardId);

        assertEquals(title, boardView.getTitle());
        assertEquals(description, boardView.getDescription());
        assertEquals(1, boardView.getAssignedUsers().size());
        assertEquals(0, boardView.getTaskData().size());
    }

}