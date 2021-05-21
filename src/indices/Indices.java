package indices;

import enums.SavefileDataType;

public class Indices {

    public static StartEnd getForSavefileDataType(SavefileDataType sdt, int offset) {
        switch (sdt) {
            case VersionMajor:          return new StartEnd(0, 2);
            case VersionMinor:          return new StartEnd(2, 4);
            case VersionPatch:          return new StartEnd(4, 6);
            case GameCount:             return new StartEnd(6, 10);
            case ChronicleNameLength:   return new StartEnd(10, 12);

            // offset dynamically set to chronicle name length between these places
            case PlayerStatus:          return new StartEnd(offset + 0, offset + 2);
            case ExileCitizenStatus:    return new StartEnd(offset + 2, offset + 4);
            case Oath:                  return new StartEnd(offset + 4, offset + 6);

            case SuitDiscord:           return new StartEnd(offset + 6, offset + 7);
            case SuitHearth:            return new StartEnd(offset + 7, offset + 8);
            case SuitNomad:             return new StartEnd(offset + 8, offset + 9);
            case SuitArcane:            return new StartEnd(offset + 9, offset + 10);
            case SuitOrder:             return new StartEnd(offset + 10, offset + 11);
            case SuitBeast:             return new StartEnd(offset + 11, offset + 12);

            case Site1:                 return new StartEnd(offset + 12, offset + 14);
            case Site2:                 return new StartEnd(offset + 20, offset + 22);
            case Site3:                 return new StartEnd(offset + 28, offset + 30);
            case Site4:                 return new StartEnd(offset + 36, offset + 38);
            case Site5:                 return new StartEnd(offset + 44, offset + 46);
            case Site6:                 return new StartEnd(offset + 52, offset + 54);
            case Site7:                 return new StartEnd(offset + 60, offset + 62);
            case Site8:                 return new StartEnd(offset + 68, offset + 70);
            case WorldDeckSize:         return new StartEnd(offset + 76, offset + 78);

            // offset dynamically set to world deck size between these places
            case DispossessedDeckSize:  return new StartEnd(offset + 0, offset + 2);

            // offset dynamically set to dispossessed deck size between these places
            case RelicDeckSize:         return new StartEnd(offset + 0, offset + 2);
        }

        return null;
    }
}
