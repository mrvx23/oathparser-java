import datatypes.*;
import enums.*;
import indices.Indices;
import indices.StartEnd;

import java.util.*;

public class OathParser {

    final static int CHRONICLE_NAME_MIN_LENGTH = 1;
    final static int CHRONICLE_NAME_MAX_LENGTH = 255;

    public OathParser() {

    }

    private String saveDataString;
    private int parseOffsetForName;
    public OathGame parseOathTTSSavefileString(String ttsSavefileString) {
        this.saveDataString = ttsSavefileString;

        OathGame game = new OathGame();

        final Integer oathMajor = getHexByIndex(SavefileDataType.VersionMajor);
        final Integer oathMinor = getHexByIndex(SavefileDataType.VersionMinor);
        final Integer oathPatch = getHexByIndex(SavefileDataType.VersionPatch);

        game.setVersion(new Version(oathMajor.toString(), oathMinor.toString(), oathPatch.toString()));

        if (oathMajor < 3 && oathMinor < 1)
            throw new RuntimeException("Oath savefile version 3.1.0 is the minimum required.");

        // the first X bytes are always going to be related to 1.6.0

        // parse game count
        game.setGameCount(getHexByIndex(SavefileDataType.GameCount));

        // get the length of the name
        final int nameLength = getHexByIndex(SavefileDataType.ChronicleNameLength);
        final int nameEnd = Indices.getForSavefileDataType(SavefileDataType.ChronicleNameLength, 0).getEnd();

        // get the name
        game.setChronicleName(saveDataString.substring(nameEnd, nameEnd + nameLength));

        // set the new offset to the game name, since everything after name needs to know the length of it
        updateOffset(nameEnd + nameLength);

        // validate the name
        if(game.getChronicleName().length() < CHRONICLE_NAME_MIN_LENGTH
                || game.getChronicleName().length() > CHRONICLE_NAME_MAX_LENGTH) {
            throw new RuntimeException("Chronicle name must be between " + CHRONICLE_NAME_MIN_LENGTH + " and " + CHRONICLE_NAME_MAX_LENGTH + " (" + game.getChronicleName() + " is " + game.getChronicleName().length());
        }

        // we throw the player status out, but it's here so it's not forgotten about
        getHexByIndex(SavefileDataType.PlayerStatus);

        // get citizenship for each player
        final int exileCitizenStatusByte = getHexByIndex(SavefileDataType.ExileCitizenStatus);
        game.setPlayerCitizenship(parseCitizenshipByte(exileCitizenStatusByte));

        // parse the oath from the game
        game.setOath(Oath.getNameByCode(getHexByIndex(SavefileDataType.Oath)));
        if(game.getOath() == null) throw new RuntimeException("Invalid Oath value was found while parsing the savefile.");

        // make sure all suit codes are found
        List<Suit> suitOrder = new ArrayList<>(6);
        suitOrder.add(getHexByIndex(SavefileDataType.SuitDiscord), Suit.Discord);
        suitOrder.add(getHexByIndex(SavefileDataType.SuitHearth), Suit.Hearth);
        suitOrder.add(getHexByIndex(SavefileDataType.SuitNomad), Suit.Nomad);
        suitOrder.add(getHexByIndex(SavefileDataType.SuitArcane), Suit.Arcane);
        suitOrder.add(getHexByIndex(SavefileDataType.SuitOrder), Suit.Order);
        suitOrder.add(getHexByIndex(SavefileDataType.SuitBeast), Suit.Beast);
        if (suitOrder.size() != 6)
            throw new RuntimeException("Not all 6 suits found while parsing the savefile.");
        game.setSuitOrder(suitOrder);

        // load sites
        List<Site> sites = new ArrayList<>();
        sites.add(loadSite(SavefileDataType.Site1));
        sites.add(loadSite(SavefileDataType.Site2));
        sites.add(loadSite(SavefileDataType.Site3));
        sites.add(loadSite(SavefileDataType.Site4));
        sites.add(loadSite(SavefileDataType.Site5));
        sites.add(loadSite(SavefileDataType.Site6));
        sites.add(loadSite(SavefileDataType.Site7));
        sites.add(loadSite(SavefileDataType.Site8));
        game.setSites(sites);

        return game;
    }


    private Site loadSite(SavefileDataType siteSlot) {
        Site site = new Site();
        StartEnd siteStartEnd = getStartEndByIndex(siteSlot);
        int siteData = getHexFromStringAsNumber(siteStartEnd.getStart(), siteStartEnd.getEnd());

        List<Card> cards = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            int cardData = getHexFromStringAsNumber(siteStartEnd.getEnd() + (2 * i), siteStartEnd.getEnd() + (2 * (i + 1)));
            cards.add(i, new Card(CardName.list[cardData]));
        }

        site.setName(SiteName.getByCode(siteData).getName());
        site.setRuined(siteData >= 24);
        site.setCards(cards);

        return site;
    }


    private int getHexFromStringAsNumber(int startIndex, int endIndex) {
        String parsedString = saveDataString.substring(startIndex, endIndex);
        // java already throws exception
        //if(!parsedString) throw new Error(`Could not parse savefile ${startIndex}...${endIndex} - returned blank.`);

        int parsedNumber = Integer.parseInt(parsedString, 16);
        // java already throws exception
        //if(isNaN(parsedNumber)) throw new Error(`Savefile ${startIndex}...${endIndex} is NaN - something is wrong.`);

        return parsedNumber;
    }

    private StartEnd getStartEndByIndex(SavefileDataType type) {
        return Indices.getForSavefileDataType(type, getParseOffsetForName());
    }

    private int getHexByIndex(SavefileDataType type) {
        StartEnd startEnd = getStartEndByIndex(type);
        return getHexFromStringAsNumber(startEnd.getStart(), startEnd.getEnd());
    }

    public int getParseOffsetForName() {
        return parseOffsetForName;
    }

    public void updateOffset(int newOffset) {
        parseOffsetForName = newOffset;
    }

    private Map<PlayerColor, Citizenship> parseCitizenshipByte(int citizenshipByte) {
        Map<PlayerColor, Citizenship> citizenships = new HashMap<PlayerColor, Citizenship>();
        citizenships.put(PlayerColor.Brown, Citizenship.Exile);
        citizenships.put(PlayerColor.Yellow, Citizenship.Exile);
        citizenships.put(PlayerColor.White, Citizenship.Exile);
        citizenships.put(PlayerColor.Blue, Citizenship.Exile);
        citizenships.put(PlayerColor.Red, Citizenship.Exile);

        if((citizenshipByte & 0x10) > 0) citizenships.put(PlayerColor.Brown, Citizenship.Citizen);
        if((citizenshipByte & 0x08) > 0) citizenships.put(PlayerColor.Yellow, Citizenship.Citizen);
        if((citizenshipByte & 0x04) > 0) citizenships.put(PlayerColor.White, Citizenship.Citizen);
        if((citizenshipByte & 0x02) > 0) citizenships.put(PlayerColor.Blue, Citizenship.Citizen);
        if((citizenshipByte & 0x01) > 0) citizenships.put(PlayerColor.Red, Citizenship.Citizen);

        return citizenships;
    }
}
