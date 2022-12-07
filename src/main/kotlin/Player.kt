class Player (val name : String, var currentRoom : String) {
    var room : Room? = null
override fun toString() : String = "Player " + name + " in " + room
    fun verifyRoom(rooms : Rooms) : Boolean{
        var r : Room? = rooms.findRoom(currentRoom)
        if (r == null){
            printError("Player verify room Room not found")
            return false
        }
        room = r
        return true
    }

}