import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.owpk.Camera
import org.owpk.ColoredCameraRoll

class CameraTest {

    def camRoll = Mockito.mock(ColoredCameraRoll.class)
    def cam

    @Before
    void setup() {
        cam = new Camera(camRoll)
    }

    @Test
    void 'should return colored roll'() {
        Mockito.when(camRoll.printPhoto()).thenReturn('mock')
        assert cam.doPhoto() == "The camera is taking photo: ${camRoll.printPhoto()}"
    }
}