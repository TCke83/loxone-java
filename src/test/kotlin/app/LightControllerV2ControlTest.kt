package cz.smarteon.loxone.app

import cz.smarteon.loxone.LoxoneUuid
import cz.smarteon.loxone.isLoxoneUuid
import cz.smarteon.loxone.readResource
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

class LightControllerV2ControlTest {

    @Test
    fun `should deserialize`() {
        expectThat(readResource<LightControllerV2Control>("app/lightControllerV2Control.json")) {
            get { name }.isEqualTo("Lichtsturing")
            get { type }.isEqualTo("LightControllerV2")
            get { uuid }.isEqualTo(LoxoneUuid("0efab4a6-0027-6e31-ffffbd85cffe7b53"))
            get { roomUuid }.isEqualTo(LoxoneUuid("0dfedf04-0074-434c-ffffbd85cffe7b53"))
            get { categoryUuid }.isEqualTo(LoxoneUuid("0dfedcb5-0096-0310-ffffbd85cffe7b53"))

            get { details }.isNotNull().and {
                get { isLockable }.isTrue()
                get { isControlHistory }.isTrue()
                get { hasHistory }.isEqualTo(20)
                get { movementScene }.isEqualTo(6)
                get { masterValue }.isEqualTo("0efab4a6-0027-6e31-ffffbd85cffe7b53/masterValue")
            }

            get { stateActiveMoods() }.isEqualTo(LoxoneUuid("16b6f3af-035f-9d2a-ffff4fda332a6ca0"))
            get { stateMoodList() }.isEqualTo(LoxoneUuid("16b6f3af-035f-9d2b-ffff4fda332a6ca0"))
            get { stateActiveMoodsNum() }.isEqualTo(LoxoneUuid("177b7ef9-00e0-51ff-ffffbd85cffe7b53"))
            get { stateFavoriteMoods() }.isEqualTo(LoxoneUuid("16b6f3af-035f-9d2c-ffff4fda332a6ca0"))
            get { stateAdditionalMoods() }.isEqualTo(LoxoneUuid("16b6f3af-035f-9d2d-ffff4fda332a6ca0"))
            get { stateCircuitNames() }.isEqualTo(LoxoneUuid("173bb674-01fd-5017-ffffbd85cffe7b53"))
            get { stateDaylightConfig() }.isEqualTo(LoxoneUuid("1977f766-0347-2a7a-ffffbd85cffe7b53"))
            get { statePresence() }.isEqualTo(LoxoneUuid("1d5b6dff-036b-9397-ffffbd85cffe7b53"))

            get { subControls }.isNotNull().and {
                get { size }.isEqualTo(9)
                get { this }.containsKey("0efab4a6-0027-6e31-ffffbd85cffe7b53/masterValue")
                get { get("0efab4a6-0027-6e31-ffffbd85cffe7b53/masterValue") }.isNotNull().and {
                    get { name }.isEqualTo("Master helderheid")
                    get { type }.isEqualTo("Dimmer")
                    get { states }.isNotNull().containsKey("position")
                }
            }
        }
    }
}
