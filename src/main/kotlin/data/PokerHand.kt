package data

class PokerHand(hand:String)
{
    private val currentHand_ = hand

    private var handList_: MutableList<Pair<String,String>> = ArrayList()

    public val currentHand:String
    get()
    {
        return currentHand_
    }

    init
    {
        handList_ = parseCurrentHand(currentHand_)
    }

    private fun parseCurrentHand(currentHand:String): MutableList<Pair<String,String>>
    {
        var tmpList: MutableList<Pair<String,String>> = ArrayList()
        val separate1 = currentHand.split(" ".toRegex())
        separate1.forEach{
            val cartArr = it.split("".toRegex())
            tmpList.add(Pair(cartArr[0],cartArr[1]))
        }

        return tmpList
    }
}