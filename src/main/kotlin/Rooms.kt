class Rooms {
    var rooms = mutableListOf<Room>()
    override fun toString() : String {
        var retval = "Rooms \n"
        for (p in rooms) {
            retval += " $p\n"
        }
        return retval
    }
    fun findRoom(name: String) : Room? {
         val retval = rooms.firstOrNull{it.name == name}
        return retval
    }
    fun addRoomFromStrings( name : String)
    {
        val room = Room(name)
        rooms.add(room)
    }
}