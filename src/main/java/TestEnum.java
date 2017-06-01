/**
 * Created by admin on 18.08.2015.
 */
public enum TestEnum {
    ACTIVE("Active"),
    DRAFT("Draft"),
    DELETED("Deleted");

    private final String value;

    TestEnum(String v) {
        value = v;
    }
/*    public static TestEnum fromValue(String v) {
        for (TestEnum c: TestEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }*/

}
