class Connections {
    override fun toString() : String {
        var retval = "Connections \n"
        for (p in connections) {
            retval += p.toString() + "\n"
        }
        return retval
    }
    var connections = mutableListOf<Connection>()
    public fun findConnection(room: Room, direction : Direction) : Connection? {

        var retval =  connections.firstOrNull{it.fromRoom == room && it.direction == direction}
        return retval
    }
    public fun findReverseConnection(room: Room, direction : Direction) : Connection? {

        val retval =  connections.firstOrNull{it.toRoom == room && it.reverseDirection == direction}

        return retval
    }
    public fun addConnectionFromStrings(fromRoomIn : String, toRoomIn : String,
                                        directionIn : String, reverseDirectionInn: String,
                                        rooms : Rooms, directions : Directions
    ) : Boolean
    {
        val fromRoom = rooms.findRoom(fromRoomIn)
            if (fromRoom == null) {
                printError("Connection fromRoom not found")
                return false
            }
        val toRoom = rooms.findRoom(toRoomIn)
        if (toRoom == null) {
            printError("Connection toRoom not found")
            return false
        }
        val direction = directions.findDirection(directionIn)
        if (direction == null) {
            printError("Connection direction not found")
            return false
        }
        val reverseDirection = directions.findDirection(reverseDirectionInn)
        if (reverseDirection == null) {
            printError("Connection reverseDirection not found")
            return false
        }
        val connection = Connection(fromRoom, toRoom, direction, reverseDirection)
        connections.add(connection)
        return true;
    }
}
