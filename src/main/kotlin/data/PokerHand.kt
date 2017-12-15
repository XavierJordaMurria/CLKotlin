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
        println(currentHand_)
        handList_ = parseCurrentHand(currentHand_)
        handList_ = sortPokerHand(handList_)


//        Tests
//        val rest:Pair<Boolean, Suits?> = isRoyalFlush(sortPokerHand(parseCurrentHand("9H JH QH KH AH")))
//        println(rest)
//
//        val rest2:Pair<Boolean, Suits?> = isStraightFlush(sortPokerHand(parseCurrentHand("AH 3H 4H 5H 6H")))
//        println(rest2)
        val rest2:Pair<Boolean, Suits?> = isPoker(sortPokerHand(parseCurrentHand("2H 2D 2S 2C 6H")))
        println(rest2)
    }

    enum class Suits(val suit:String)
    {
        HEARTS("H"),
        CLUBS("C"),
        SPADES("S"),
        DIAMONDS("D")
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

    enum class CartType(val value:String)
    {
        TWO("2"),THREE("3"),FOUR("4"),FIVE("5"), SIX("6"),SEVEN("7"), EIGHT("8"),NINE("9"),
        TEN("T"),JACK("J"),QUEEN("Q"),KING("K"),ACE("A")
    }

    private fun parseCurrentHand(currentHand:String): List<Pair<CartType?,Suits?>>
    {
        val tmpList: MutableList<Pair<CartType?,Suits?>> = ArrayList()
        val separate1 = currentHand.split(" ".toRegex())
        separate1.forEach{
            val cartArr = it.split("".toRegex())
            println("cartType:" + cartArr[0] + " suit:" +cartArr[1])

            tmpList.add(Pair(convertCart(cartArr[0]),convertSuit(cartArr[1])))
        }

        return tmpList
    }

    private fun sortPokerHand(handList: List<Pair<CartType?,Suits?>>):List<Pair<CartType?,Suits?>>
    {
        return handList.sortedWith(compareBy({
            it.first!!.ordinal
        }))
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
            if (it.second!!.suit != suit!!.suit)
                return Pair(false, null)
            else {
                when {
                    it.first!!.value == CartType.TEN.value -> gotTen = true
                    it.first!!.value == CartType.JACK.value -> gotJack = true
                    it.first!!.value == CartType.QUEEN.value -> gotQueen = true
                    it.first!!.value == CartType.KING.value -> gotKing = true
                    it.first!!.value == CartType.ACE.value -> gotAce = true
                }
            }
        }

        return Pair(gotTen && gotJack && gotQueen && gotKing && gotAce,suit)
    }

    private fun isStraightFlush(handList:List<Pair<CartType?,Suits?>>):Pair<Boolean, Suits?>
    {
        val suit: Suits? = handList[0].second
        var firstCardPosition:Int = handList[0].first!!.ordinal

        handList.drop(1).forEach{
            //if the suit is different can not be royal flush anymore
            if (it.second!!.suit != suit!!.suit)
                return Pair(false, suit)
            else {
                if (firstCardPosition + 1  == it.first!!.ordinal || firstCardPosition % CartType.values().size == it.first!!.ordinal)
                    firstCardPosition = it.first!!.ordinal
                else
                    return Pair(false, suit)
            }
        }

        return Pair(true,suit)
    }

    private fun isPoker(handList:List<Pair<CartType?,Suits?>>):Pair<Boolean, Suits?>
    {

        val firstCart = handList[0].first!!.value
        val secondCart = handList[0].first!!.value

        val firstPoker = handList.count { it.first!!.value == firstCart}


        return Pair(false, handList[0].second)
    }

    private fun convertSuit(suit:String):Suits?
            = when (suit) {
                Suits.HEARTS.suit -> Suits.HEARTS
                Suits.CLUBS.suit -> Suits.CLUBS
                Suits.SPADES.suit -> Suits.SPADES
                Suits.DIAMONDS.suit -> Suits.DIAMONDS
                else -> { // Note the block
                    println("no suit match suit:" + suit)
                    null
                }
            }

    private fun convertCart(cart:String):CartType?
            = when (cart) {
        CartType.TWO.value -> CartType.TWO
        CartType.THREE.value -> CartType.THREE
        CartType.FOUR.value -> CartType.FOUR
        CartType.FIVE.value -> CartType.FIVE
        CartType.SIX.value -> CartType.SIX
        CartType.SEVEN.value -> CartType.SEVEN
        CartType.EIGHT.value -> CartType.EIGHT
        CartType.NINE.value -> CartType.NINE
        CartType.TEN.value -> CartType.TEN
        CartType.JACK.value -> CartType.JACK
        CartType.QUEEN.value -> CartType.QUEEN
        CartType.KING.value -> CartType.KING
        CartType.ACE.value -> CartType.ACE

        else -> { // Note the block
            println("no cart match cart:" + cart)
            null
        }
    }
}