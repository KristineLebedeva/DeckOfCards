package api;

public final class EndPoints {

    public static final String GET_DECK = "/deck/{0}";
    public static final String GET_PILE = "/deck/{0}/pile/{1}/list/";
    public static final String DRAW_CARDS = "/deck/{0}/draw/";
    public static final String CREATE_NEW_DECK = "/deck/new/shuffle/";
    public static final String MOVE_CARDS_TO_PILE = "deck/{0}/pile/{1}/add/";
    public static final String DRAW_CARDS_FROM_PILE = "/deck/{0}/pile/{1}/draw/{2}/";

}
