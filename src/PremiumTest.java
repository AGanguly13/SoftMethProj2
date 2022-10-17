import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PremiumTest {

    @Test
    void membership_fee_constant() {
        Premium member1 = new Premium("Gary Smith 7/2/1989 bridgewater");
        assertEquals(59.99*11, member1.membershipFee());
    }
}