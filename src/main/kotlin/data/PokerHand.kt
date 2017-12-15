package data

class PokerHand(hand:String)
{
    private val currentHand_ = hand

    private var handList_: List<Pair<CartType?,Suits?>> = ArrayList()

    public val currentHand:String
    get()
    {
        return currentHand_
    }

    init
    {
        handList_ = parseCurrentHand(currentHand_)
        handList_ = sortPokerHand(handList_)
        handList_ = parseCurrentHand(currentHand_)
    }

    enum class Suits(val suit:String)
    {
        HEARTS("H"),
        CLUBS("C"),
        SPADES("S"),
        DIAMONDS("D")
    }

    enum class Figures(val figure:String)
    {
        //Figures
        TEN("T"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ACE("A")
    }

    //Poker Hands
    enum class PokerHands(val hand:String)
    {
        ROYAL_FLUSH("RoyalFLush"),
        STRAIGHT_FLUSH("StraightFlush"),
        POKER("Poker"),
        FULL_HOUSE( "FullHouse"),
        FLUSH("Flush"),
        STRAIGHT("Straight"),
        THREE_KIND("ThreeOfAKind"),
        TWO_PAIRS("TwoPairs"),
        ONE_PAIR("OnePairs"),
        HIGH_CARD("HighCard")
    }

    enum class CartType(val value:Int)
    {
        TWO(2),THREE(3),FOUR(4),FIVE(5), SIX(6),SEVEN(7), EIGHT(8),NINE(9),
        TEN(10),JACK(11),QUEEN(12),KING(13),ACE(14)
    }

    private fun parseCurrentHand(currentHand:String): List<Pair<CartType?,Suits?>>
    {
        val tmpList: MutableList<Pair<CartType?,Suits?>> = ArrayList()
        val separate1 = currentHand.split(" ".toRegex())
        separate1.forEach{
            val cartArr = it.split("".toRegex())
            tmpList.add(Pair(convertCart(cartArr[0]),convertSuit(cartArr[1])))
        }

        return tmpList
    }

    private fun sortPokerHand(handList: List<Pair<CartType?,Suits?>>):List<Pair<CartType?,Suits?>>
    {
        return handList.sortedWith(compareBy({ it.first!!.ordinal }))
    }

    private fun analyseHand(handList:List<Pair<String,String>>)
    {

    }


    private fun isRoyalFlush(handList:List<Pair<CartType?,Suits?>>):Pair<Boolean, Suits?>
    {
        val suit: Suits? = handList[0].second

        var gotTen  = false
        var gotJack = false
        var gotQueen    =  false
        var gotKing =   false
        var gotAce  =   false

        handList.forEach{
            //if the suit is different can not be royal flush anymore
            if (it.second != suit)
                return Pair(false, null)
            else {
                when {
                    it.first!!.name == Figures.TEN.name -> gotTen = true
                    it.first!!.name == Figures.JACK.name -> gotJack = true
                    it.first!!.name == Figures.QUEEN.name -> gotQueen = true
                    it.first!!.name == Figures.KING.name -> gotKing = true
                    it.first!!.name == Figures.ACE.name -> gotAce = true
                }
            }
        }

        return Pair(gotTen && gotJack && gotQueen && gotKing && gotAce,suit)
    }

    private fun convertSuit(suit:String):Suits?
            = when (suit) {
                Suits.HEARTS.name -> Suits.HEARTS
                Suits.CLUBS.name -> Suits.CLUBS
                Suits.SPADES.name -> Suits.SPADES
                Suits.DIAMONDS.name -> Suits.DIAMONDS
                else -> { // Note the block
                    print("no suit match")
                    null
                }
            }

    private fun convertCart(cart:String):CartType?
            = when (cart) {
        CartType.TWO.name -> CartType.TWO
        CartType.THREE.name -> CartType.THREE
        CartType.FOUR.name -> CartType.FOUR
        CartType.FIVE.name -> CartType.FIVE
        CartType.SIX.name -> CartType.SIX
        CartType.SEVEN.name -> CartType.SEVEN
        CartType.EIGHT.name -> CartType.EIGHT
        CartType.NINE.name -> CartType.NINE
        CartType.TEN.name -> CartType.TEN
        CartType.JACK.name -> CartType.JACK
        CartType.QUEEN.name -> CartType.QUEEN
        CartType.KING.name -> CartType.KING
        CartType.ACE.name -> CartType.ACE

        else -> { // Note the block
            print("no suit match")
            null
        }
    }
}