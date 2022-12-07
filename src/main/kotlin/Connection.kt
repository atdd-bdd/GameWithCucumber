class Connection (val fromRoom : Room, val toRoom : Room,
                  val direction : Direction, val reverseDirection : Direction
) {
    override fun toString() : String {
        return " connection $fromRoom to $toRoom $direction $reverseDirection"
    }
}