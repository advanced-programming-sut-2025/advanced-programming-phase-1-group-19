package Modules.Map;

public enum FarmMap {
    first(0),
    second(1),
    third(2),
    fourth(3),
    ;

    private final Size size;
    private final Position housePosition;
    private final Size houseSize;
//    4*4
    private final Position greenHousePosition;
    private final Size greenHouseSize;
//    5*6
    private final Position lakePosition;
    private final Size lakeSize;
    private final Position quarterPosition;
    private final Size quarterSize;

    FarmMap(int id) {
        size = new Size(100, 100);

        switch (id) {
            case 0: {
                housePosition = new Position(12, 10);
                houseSize = new Size(4, 4);

                greenHousePosition = new Position(8, 22);
                greenHouseSize = new Size(5, 6);

                lakePosition = new Position(54, 9);
                lakeSize = new Size(5, 5);

                quarterPosition = new Position(78, 80);
                quarterSize = new Size(6, 4);
                break;
            }
            case 1: {
                housePosition = new Position(66, 5);
                houseSize = new Size(4, 4);

                greenHousePosition = new Position(13, 73);
                greenHouseSize = new Size(5, 6);

                lakePosition = new Position(21, 14);
                lakeSize = new Size(4, 5);

                quarterPosition = new Position(83, 65);
                quarterSize = new Size(4, 5);
                break;
            }
            case 2: {
                housePosition = new Position(74, 59);
                houseSize = new Size(4, 4);

                greenHousePosition = new Position(31, 15);
                greenHouseSize = new Size(5, 6);

                lakePosition = new Position(81, 9);
                lakeSize = new Size(6, 5);

                quarterPosition = new Position(22, 68);
                quarterSize = new Size(6, 6);
                break;
            }
            case 3: {
                housePosition = new Position(21, 75);
                houseSize = new Size(4, 4);

                greenHousePosition = new Position(82, 17);
                greenHouseSize = new Size(5, 6);

                lakePosition = new Position(15, 12);
                lakeSize = new Size(6, 5);

                quarterPosition = new Position(58, 73);
                quarterSize = new Size(6, 6);
                break;
            }
            default: {
                housePosition = null;
                houseSize = null;
                greenHousePosition = null;
                greenHouseSize = null;
                lakePosition = null;
                lakeSize = null;
                quarterPosition = null;
                quarterSize = null;
                break;
            }
        }
//        TODO: build starting map of farm
    }

    public Size getSize() {
        return size;
    }

    public Position getHousePosition() {
        return housePosition;
    }

    public Size getHouseSize() {
        return houseSize;
    }

    public Position getGreenHousePosition() {
        return greenHousePosition;
    }

    public Size getGreenHouseSize() {
        return greenHouseSize;
    }

    public Position getLakePosition() {
        return lakePosition;
    }

    public Size getLakeSize() {
        return lakeSize;
    }

    public Position getQuarterPosition() {
        return quarterPosition;
    }

    public Size getQuarterSize() {
        return quarterSize;
    }
}
