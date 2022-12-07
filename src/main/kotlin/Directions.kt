class Directions {
    var directions = mutableListOf<Direction>()
    override fun toString() : String {
        var retval = "Directions \n"
        for (p in directions) {
            retval += " $p\n"
        }
        return retval
    }
    fun findDirection(name: String) : Direction? {
        return  directions.firstOrNull{it.name == name}

    }
    fun addDirectionFromStrings( name : String) : Boolean
    {
        val direction = Direction(name)
        directions.add(direction)
        return true;
    }

}