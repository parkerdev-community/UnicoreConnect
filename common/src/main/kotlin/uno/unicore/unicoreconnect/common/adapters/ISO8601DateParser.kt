package uno.unicore.unicoreconnect.common.adapters

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * ISO 8601 date parsing utility.  Designed for parsing the ISO subset used in
 * Dublin Core, RSS 1.0, and Atom.
 *
 * @author [Kevin A. Burton (burtonator)](mailto:burton@apache.org)
 * @version $Id: ISO8601DateParser.java,v 1.2 2005/06/03 20:25:29 snoopdave Exp $
 */
object ISO8601DateParser {
    // 2004-06-14T19:GMT20:30Z
    // 2004-06-20T06:GMT22:01Z
    // http://www.cl.cam.ac.uk/~mgk25/iso-time.html
    //
    // http://www.intertwingly.net/wiki/pie/DateTime
    //
    // http://www.w3.org/TR/NOTE-datetime
    //
    // Different standards may need different levels of granularity in the date and
    // time, so this profile_details defines six levels. Standards that reference this
    // profile_details should specify one or more of these granularities. If a given
    // standard allows more than one granularity, it should specify the meaning of
    // the dates and times with reduced precision, for example, the result of
    // comparing two dates with different precisions.
    // The formats are as follows. Exactly the components shown here must be
    // present, with exactly this punctuation. Note that the "T" appears literally
    // in the string, to indicate the beginning of the time element, as specified in
    // ISO 8601.
    //    Year:
    //       YYYY (eg 1997)
    //    Year and month:
    //       YYYY-MM (eg 1997-07)
    //    Complete date:
    //       YYYY-MM-DD (eg 1997-07-16)
    //    Complete date plus hours and minutes:
    //       YYYY-MM-DDThh:mmTZD (eg 1997-07-16T19:20+01:00)
    //    Complete date plus hours, minutes and seconds:
    //       YYYY-MM-DDThh:mm:ssTZD (eg 1997-07-16T19:20:30+01:00)
    //    Complete date plus hours, minutes, seconds and a decimal fraction of a
    // second
    //       YYYY-MM-DDThh:mm:ss.sTZD (eg 1997-07-16T19:20:30.45+01:00)
    // where:
    //      YYYY = four-digit year
    //      MM   = two-digit month (01=January, etc.)
    //      DD   = two-digit day of month (01 through 31)
    //      hh   = two digits of hour (00 through 23) (am/pm NOT allowed)
    //      mm   = two digits of minute (00 through 59)
    //      ss   = two digits of second (00 through 59)
    //      s    = one or more digits representing a decimal fraction of a second
    //      TZD  = time zone designator (Z or +hh:mm or -hh:mm)
    @Throws(ParseException::class)
    fun parse(input: String?): Date {
        var input = input ?: throw ParseException("Null", 0)

        //NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
        //things a bit.  Before we go on we have to repair this.
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        val df2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz")


        //this is zero time so we need to add that TZ indicator for
        input = if (input.endsWith("Z")) {
            input.substring(0, input.length - 1) + "GMT-00:00"
        } else {
            val inset = 6
            val s0 = input.substring(0, input.length - inset)
            val s1 = input.substring(input.length - inset, input.length)
            s0 + "GMT" + s1
        }
        return if (input.contains(".")) {
            df2.parse(input)
        } else df.parse(input)
    }

    fun format(date: Date?): String {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        val tz = TimeZone.getTimeZone("UTC")
        df.timeZone = tz
        val output = df.format(date)
        return output.replace("UTC".toRegex(), "+00:00")
    }
}