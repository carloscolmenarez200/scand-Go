package com.cencosud.scanandgo.wallet.utils;

import android.text.TextUtils;
import android.util.Pair;

import com.cencosud.scanandgo.R;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by carlos on 27-03-18.
 */

public enum NameCard {
    /**
     * American Express cards start in 34 or 37
     */
    AMEX("AMEX"),
    /**
     * Diners Club
     */
    DINERSCLUB("DINERSCLUB"),
    /**
     * Discover starts with 6x for some values of x.
     */
    DISCOVER("DISCOVER"),
    /**
     * JCB (see http://www.jcbusa.com/) cards start with 35
     */
    JCB("JCB"),
    /**
     * Mastercard starts with 51-55
     */
    MASTERCARD("MASTERCARD"),
    /**
     * Mastercard starts with 51-55
     */
    MASTER_CENCOSUD("MASTER_CENCOSUD"),
    /**
     * Visa starts with 4
     */
    VISA("VISA"),
    /**
     * Maestro
     */
    MAESTRO("MAESTRO"),
    /**
     * Unknown card type.
     */
    UNKNOWN("UNKNOWN"),
    /**
     * Not enough information given.
     * <br><br>
     * More digits are required to know the card type. (e.g. all we have is a 3, so we don't know if
     * it's JCB or AmEx)
     */
    INSUFFICIENT_DIGITS("INSUFFICIENT_DIGITS");

    public final String name;

    private static int minDigits = 1;

    NameCard(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getDisplayName(String languageOrLocale) {
        return name;
        /*switch (this) {
            case AMEX:
                return AXLocalizedStrings.getString(StringKey.CARDTYPE_AMERICANEXPRESS, languageOrLocale);
            case DINERSCLUB:
            case DISCOVER:
                return LocalizedStrings.getString(StringKey.CARDTYPE_DISCOVER, languageOrLocale);
            case JCB:
                return LocalizedStrings.getString(StringKey.CARDTYPE_JCB, languageOrLocale);
            case MASTERCARD:
                return LocalizedStrings.getString(StringKey.CARDTYPE_MASTERCARD, languageOrLocale);
            case MAESTRO:
                return LocalizedStrings.getString(StringKey.CARDTYPE_MAESTRO, languageOrLocale);
            case VISA:
                return LocalizedStrings.getString(StringKey.CARDTYPE_VISA, languageOrLocale);
            default:
                break;
        }
        return null;*/
    }

    /**
     * @return 15 for AmEx, -1 for unknown, 16 for others.
     */
    public int numberLength() {
        int result;
        switch (this) {
            case AMEX:
                result = 15;
                break;
            case JCB:
            case MASTERCARD:
            case MAESTRO:
            case VISA:
            case DISCOVER:
                result = 16;
                break;
            case DINERSCLUB:
                result = 14;
                break;
            case INSUFFICIENT_DIGITS:
                // this represents the maximum number of digits before we can know the card type
                result = minDigits;
                break;
            case UNKNOWN:
            default:
                result = -1;
                break;
        }
        return result;
    }

    /**
     * @return 4 for Amex, 3 for others, -1 for unknown
     */
    public int cvvLength() {
        int result;
        switch (this) {
            case AMEX:
                result = 4;
                break;
            case JCB:
            case MASTERCARD:
            case MAESTRO:
            case VISA:
            case DISCOVER:
            case DINERSCLUB:
                result = 3;
                break;
            case UNKNOWN:
            default:
                result = -1;
                break;
        }

        return result;
    }

    /**
     * Determine if a number matches a prefix interval
     *
     * @param number credit card number
     * @param intervalStart prefix (e.g. "4") or prefix interval start (e.g. "51")
     * @param intervalEnd prefix interval end (e.g. "55") or null for non-intervals
     * @return -1 for insufficient digits, 0 for no, 1 for yes.
     */
    private static boolean isNumberInInterval(String number, String intervalStart,
                                              String intervalEnd) {
        // Log.d("NameCard", "numberInInterval(number:" + number + ",intervalStart:" + intervalStart
        // + ",intervalEnd:" + intervalEnd + ")");

        int numCompareStart = Math.min(number.length(), intervalStart.length());
        int numCompareEnd = Math.min(number.length(), intervalEnd.length());

        if (Integer.parseInt(number.substring(0, numCompareStart)) < Integer.parseInt(intervalStart
                .substring(0, numCompareStart))) {
            // number is too low
            return false;
        } else if (Integer.parseInt(number.substring(0, numCompareEnd)) > Integer
                .parseInt(intervalEnd.substring(0, numCompareEnd))) {
            // number is too high
            return false;
        }

        return true;
    }

    private static HashMap<Pair<String, String>,NameCard> intervalLookup;

    static {
        // initialize
        intervalLookup = new HashMap<>();
        intervalLookup.put(getNewPair("2221", "2720"), NameCard.MASTERCARD);    // MasterCard 2-series
        intervalLookup.put(getNewPair("300", "305"), NameCard.DINERSCLUB);      // Diners Club (Discover)
        intervalLookup.put(getNewPair("309", null), NameCard.DINERSCLUB);       // Diners Club (Discover)
        intervalLookup.put(getNewPair("34", null), NameCard.AMEX);              // AmEx
        intervalLookup.put(getNewPair("3528", "3589"), NameCard.JCB);           // JCB
        intervalLookup.put(getNewPair("36", null), NameCard.DINERSCLUB);        // Diners Club (Discover)
        intervalLookup.put(getNewPair("37", null), NameCard.AMEX);              // AmEx
        intervalLookup.put(getNewPair("38", "39"), NameCard.DINERSCLUB);        // Diners Club (Discover)
        intervalLookup.put(getNewPair("4", null), NameCard.VISA);               // Visa
        intervalLookup.put(getNewPair("50", null), NameCard.MAESTRO);           // Maestro
        intervalLookup.put(getNewPair("51", "55"), NameCard.MASTERCARD);        // MasterCard
        intervalLookup.put(getNewPair("56", "59"), NameCard.MAESTRO);           // Maestro
        intervalLookup.put(getNewPair("6011", null), NameCard.DISCOVER);        // Discover
        intervalLookup.put(getNewPair("61", null), NameCard.MAESTRO);           // Maestro
        intervalLookup.put(getNewPair("62", null), NameCard.DISCOVER);          // China UnionPay (Discover)
        intervalLookup.put(getNewPair("63", null), NameCard.MAESTRO);           // Maestro
        intervalLookup.put(getNewPair("644", "649"), NameCard.DISCOVER);        // Discover
        intervalLookup.put(getNewPair("65", null), NameCard.DISCOVER);          // Discover
        intervalLookup.put(getNewPair("66", "69"), NameCard.MAESTRO);           // Maestro
        intervalLookup.put(getNewPair("88", null), NameCard.DISCOVER);          // China UnionPay (Discover)

        for (Map.Entry<Pair<String, String>,NameCard> entry : getIntervalLookup().entrySet()) {
            minDigits = Math.max(minDigits, entry.getKey().first.length());
            if (entry.getKey().second != null) {
                minDigits = Math.max(minDigits, entry.getKey().second.length());
            }
        }
    }

    private static HashMap<Pair<String, String>,NameCard> getIntervalLookup() {
        return intervalLookup;
    }

    private static Pair<String, String> getNewPair(String intervalStart, String intervalEnd) {
        if (intervalEnd == null) {
            // set intervalEnd to intervalStart before creating the Pair object, because apparently
            // Pair.hashCode() can't handle nulls on some devices/versions. WTF.
            intervalEnd = intervalStart;
        }
        return new Pair<String, String>(intervalStart, intervalEnd);
    }

    /**
     * Infer the card type from a string.
     *
     * @param typeStr The String value of this enum
     * @return the matched real type
     */
    public static NameCard fromString(String typeStr) {
        if (typeStr == null) {
            return NameCard.UNKNOWN;
        }

        for (NameCard type : NameCard.values()) {
            if (type == NameCard.UNKNOWN || type == NameCard.INSUFFICIENT_DIGITS) {
                continue;
            }

            if (typeStr.equalsIgnoreCase(type.toString())) {
                return type;
            }
        }
        return NameCard.UNKNOWN;
    }

    /**
     * Infer the NameCard from the number string. See http://en.wikipedia.org/wiki/Bank_card_number
     * for these ranges (last checked: 19 Feb 2013)
     *
     * @param numStr A string containing only the card number.
     * @return the inferred card type
     */
    public static NameCard fromCardNumber(String numStr) {
        if (TextUtils.isEmpty(numStr)) {
            return NameCard.UNKNOWN;
        }

        HashSet<NameCard> possibleNameCards = new HashSet<>();
        for (Map.Entry<Pair<String, String>, NameCard> entry : getIntervalLookup().entrySet()) {
            boolean isPossibleCard = isNumberInInterval(numStr, entry.getKey().first,
                    entry.getKey().second);
            if (isPossibleCard) {
                possibleNameCards.add(entry.getValue());
            }
        }

        if (possibleNameCards.size() > 1) {
            return NameCard.INSUFFICIENT_DIGITS;
        } else if (possibleNameCards.size() == 1) {
            return possibleNameCards.iterator().next();
        } else {
            return NameCard.UNKNOWN;
        }
    }

    public int getCardBackground() {
        if(this.equals(NameCard.VISA)){
            return R.drawable.visa_background;
        }else if(this.equals(NameCard.MASTERCARD)){
            return R.drawable.mastercard_background;
        }else if(this.equals(NameCard.AMEX)){
            return R.drawable.amex_background;
        }else if(this.equals(NameCard.DINERSCLUB)|| this.equals(NameCard.DISCOVER)){
            return R.drawable.dinners_background;
        }else if(this.equals(NameCard.JCB)){
            return R.drawable.jcb_background;
        }
        return R.drawable.jcb_background; //default
    }
}

