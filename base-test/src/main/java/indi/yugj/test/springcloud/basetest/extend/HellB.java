package indi.yugj.test.springcloud.basetest.extend;

/**
 * Description:
 * Created by yugj on 18/7/14 18:17.
 */
public class HellB extends BaseHell{

    HellB(String key, String value) {
        super(key, value);
    }

    @Override
    public String hell() {
        return "hell-b," + this.key + "-" + this.value;
    }
}
