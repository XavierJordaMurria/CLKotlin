package data

class PockerHand(hand:String)
{
    private val currentHand_ = hand

    public val currentHand:String
    get()
    {
        return currentHand_
    }

    init()
}