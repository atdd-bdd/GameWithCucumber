
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.junit.jupiter.api.Assertions.assertEquals


var lastInstance: LambdaStepDefinitions? = null
/*
*   How to do this now in Java - using DataTableType annotation !!!!
*   @DataTableType
    public Author authorEntry(Map<String, String> entry) {
        return new Author(
            entry.get("firstName"),
            entry.get("lastName"),
            entry.get("famousBook"));
    }

    @Given("There are my favorite authors")
    public void these_are_my_favourite_authors(List<Author> authors) {
        // step implementation
    }
 */

data class DirectionInput(val value: String?)
data class RoomInput(val name: String?)
data class ConnectionInput(
    val fromRoom: String?, val toRoom: String?,
    val direction: String?,
    val reverseDirection: String?,
)

data class PlayerInput(val name: String?, val room: String?)
data class CommandInput(val verb: String?, val noun: String?)

data class MoveDirectionInput(val direction: String?, val notes: String?)
data class Something(val head: String?, val head1: String?, val head2: String?)
data class Person(val first: String?, val last: String?)
data class Input(val value: String?)
data class Output(val value: String?)
class LambdaStepDefinitions : En {
    var directions = Directions()
    var rooms = Rooms()
    var connections = Connections()
    var gameSpace = GameSpace(rooms, connections, directions)
    var player = Player("sam", "initial")
    var output : String = ""

    init {
        DataTableType { entry: Map<String, String> ->
            Input(entry["Input"])
        }
        DataTableType { entry: Map<String, String> ->
            Output(entry["Output"])
        }
        DataTableType { entry: Map<String, String> ->
            Person(entry["first"], entry["last"])
        }

        DataTableType { entry: Map<String, String> ->
            Something(entry["head"], entry["head1"], entry["head2"])
        }

        DataTableType { entry: Map<String, String> ->
            MoveDirectionInput(entry["direction"], entry["notes"])
        }
        DataTableType { entry: Map<String, String> ->
            DirectionInput(entry["Direction"])
        }
        DataTableType { entry: Map<String, String> ->
            ConnectionInput(entry["FromRoom"], entry["ToRoom"], entry["Direction"], entry["ReverseDirection"])
        }
        DataTableType { entry: Map<String, String> ->
            RoomInput(entry["Name"])
        }
        DataTableType { entry: Map<String, String> ->
            PlayerInput(entry["Name"], entry["CurrentRoom"])
        }

        DataTableType { entry: Map<String, String> ->
            CommandInput(entry["Verb"], entry["Noun"])
        }
        Given(
            "Directions are:"
        ) { dataTable: DataTable? ->
            val list: List<DirectionInput> = dataTable!!.asList(DirectionInput::class.java)
             for (p in list) {
                directions.addDirectionFromStrings(p.value!!)
            }
        }
        Given(
            "rooms are"
        ) { dataTable: DataTable? ->
            val list: List<RoomInput> = dataTable!!.asList(RoomInput::class.java)
            for (p in list) {
                rooms.addRoomFromStrings(p.name!!)
            }
        }
        Given(
            "connections are"
        ) { dataTable: DataTable? ->
            val list: List<ConnectionInput> = dataTable!!.asList(ConnectionInput::class.java)
            for (p in list) {
                connections.addConnectionFromStrings(
                    p.fromRoom!!, p.toRoom!!,
                    p.direction!!, p.reverseDirection!!, rooms!!, directions!!
                )
            }
        }
        Given(
            "player is"
        ) { dataTable: DataTable? ->
            player = playerFromTable(dataTable)
            if (!player.verifyRoom(rooms)) {
                printError("Player in step def Room not found")
            }
        }
        When(
            "command is"
        ) { dataTable: DataTable? ->
            gameSpace = GameSpace(rooms, connections, directions)
            val command = commandFromTable(dataTable)
            gameSpace.executeMove(command, player)
        }
        Then(
            "person is now"
        ) { dataTable: DataTable? ->
            val expectedPlayer = playerFromTable(dataTable)
            if (!expectedPlayer.verifyRoom(rooms)) {
                printError("Player in Expected Room not found")
            }
            assertEquals(expectedPlayer.room, player.room)
        }

        When(
            "input is"
        ) { dataTable: DataTable? ->
            val input: String = inputFromTable(dataTable)
             gameSpace = GameSpace(rooms, connections, directions)
            output = gameSpace.executeCommandString(input, player)
        }
        Then(
            "output is"
        ) { dataTable: DataTable? ->
            val expected: String = outputFromTable(dataTable)
            assertEquals(expected, output)
        }
        When("run live") {
            gameSpace = GameSpace(rooms, connections, directions)
            var input = ""
            while (input != "exit") {
                input = readln()
                output = gameSpace.executeCommandString(input, player)
                println(output)
            }
        }

    }
    private fun inputFromTable(dataTable: DataTable?) : String {
        val list: List<Input> = dataTable!!.asList(Input::class.java)
        val first = list[0]
        val retval = first.value
        return retval!!
    }
    private fun outputFromTable(dataTable: DataTable?) : String {
        val list: List<Output> = dataTable!!.asList(Output::class.java)
        val first = list[0]
        val retval = first.value
        return retval!!
    }

    private fun playerFromTable(dataTable: DataTable?) : Player {
        val list: List<PlayerInput> = dataTable!!.asList(PlayerInput::class.java)
        val first = list[0]
        val player = Player(first.name!!, first.room!!)
        return player
    }


    private fun commandFromTable(dataTable: DataTable?): Command {
        val list: List<CommandInput> = dataTable!!.asList(CommandInput::class.java)
        val first = list[0]
        val command = Command(first.verb!!, first.noun)
        return command
    }
}



