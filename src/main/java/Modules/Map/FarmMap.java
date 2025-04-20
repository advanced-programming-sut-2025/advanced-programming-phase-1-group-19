package Modules.Map;

public enum FarmMap {
    first(0),
    second(1),
    third(2),
    fourth(3),
    ;

    private final Size size;
    private final Position housePosition;
//    4*4
    private final Position greenHousePosition;
//    5*6
    private final Position lakePosition;
    private final Size lakeSize;
    private final Position quarterPosition;
    private final Size quarterSize;

    FarmMap(int id) {
//        TODO: build starting map of farm
    }

}
