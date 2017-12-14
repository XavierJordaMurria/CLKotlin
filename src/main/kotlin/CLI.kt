package com.clkotlin

class CLI
{
    private var filePath_ = ""

    public val filePath:String
    get(){
        return filePath_
    }

    constructor(args: Array<String>)
    {
        parse(args)
    }

    private fun parse(args: Array<String>)
    {
        if (args.isEmpty())
            throw Throwable("File path not provided!")

        filePath_ = args[0]
    }
}
