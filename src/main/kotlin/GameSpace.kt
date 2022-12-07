class GameSpace constructor (var rooms : Rooms, var connections : Connections,
                             var directions : Directions
) {
    override fun toString(): String {
    var retval = "Gamespace: \n"
    retval += rooms
    retval += directions
    retval += connections
    return retval
}
    fun movePlayer(player: Player, direction: Direction) : Boolean {
        var currentRoom = player.room
        var connection = connections.findConnection(currentRoom!!, direction)
        if (connection != null){
            player.room = connection.toRoom
            return true;
             }
        connection = connections.findReverseConnection(currentRoom!!, direction)
        if (connection != null){
            player.room = connection.fromRoom
            return true;
        }
        printError("Connection not found " + player.room + " " + direction)
            return false;
        }

    fun executeMove(command: Command, player: Player) : Boolean {
        val direction: Direction? = command.verifyVerb(directions)
        // Check if a different  type of command
        if (direction == null) {
            printError("Unknown direction for command " + command.verb)
            return false;
        }
        val result = movePlayer(player, direction!!)
        if (!result) {
            printError("Move could not be made")
            return false
        }
        val currentRoom = player.room!!
        return true
    }

    fun executeCommandString(input: String, player: Player): String {
        val command = Command(input, "")
        val result = executeMove(command, player)
        var output = ""
        if (result) {
            val currentRoom: Room = player.room!!
             output = "You are in " + currentRoom
        }
        else
            output = "Could not do that"

        return output
    }


}