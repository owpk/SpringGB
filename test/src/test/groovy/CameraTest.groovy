import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.owpk.Camera
import org.owpk.CameraRoll

class CameraTest {

    def camRoll = Mockito.mock(CameraRoll.class)
    def cam

    @Before
    void setup() {
        Mockito.when(camRoll.printPhoto()).thenReturn('mock')
        cam = new Camera(camRoll)
    }

    @Test
    void 'should make photo'() {
        assert cam.doPhoto() == "The camera is taking photo: ${camRoll.printPhoto()}"
    }
}