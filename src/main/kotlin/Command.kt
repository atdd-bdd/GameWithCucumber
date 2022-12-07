class Command (val verb:String, val noun : String?) {

    override fun toString() : String {
        return verb + " " + noun
    }
    fun verifyVerb(directions : Directions) : Direction? {
        var direction: Direction? = directions.findDirection(verb)
        if (direction == null){
            printError("Direction not found")
        }
        return direction
     }
}