package org.coner.crispy_fish.filetype.st1;

import org.coner.crispy_fish.filetype.ecf.EcfAssistant;

import java.io.File;
import java.nio.file.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class St1FileLocatorTest {

    private St1FileLocator st1FileLocator;

    @Mock
    EcfAssistant ecfAssistant;
    @Mock
    Path eventControlFile;
    @Mock
    File eventControlFileParentAsFile;
    @Mock
    St1FilenameFilter st1FilenameFilter;

    @Before
    public void setup() {
        st1FileLocator = new St1FileLocator(ecfAssistant);
    }

    @Test
    public void whenLocateInvalidEventControlFileItShouldThrow() throws Exception {
        eventControlFile = mock(Path.class);
        when(ecfAssistant.isEcf(eventControlFile)).thenReturn(false);

        try {
            st1FileLocator.locate(eventControlFile);
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test(expected = IllegalStateException.class)
    public void whenLocateWithoutEventControlFileItShouldThrow() throws Exception {
        st1FileLocator.locate(null);
    }

    @Test
    public void whenLocateWithSimpleSt1ItShouldLocateSt1() {
        when(ecfAssistant.isEcf(eventControlFile)).thenReturn(true);
        Path ecfParent = mock(Path.class);
        when(eventControlFile.getParent()).thenReturn(ecfParent);
        when(ecfParent.toFile()).thenReturn(eventControlFileParentAsFile);
        when(ecfAssistant.buildSt1FilenameFilter(eventControlFile)).thenReturn(st1FilenameFilter);
        Path ecfPath = Paths.get("foo.ecf");
        File[] files = new File[]{ecfPath.toFile()};
        when(eventControlFileParentAsFile.listFiles(st1FilenameFilter)).thenReturn(files);

        Path actual = st1FileLocator.locate(eventControlFile);

        assertThat(actual).isEqualTo(ecfPath);
    }

    @Test
    public void whenSelectFileWithSimpleSt1ItShouldSelectIt() {
        File expected = Paths.get("foo.st1").toFile();
        File[] files = new File[]{
                expected
        };

        File actual = st1FileLocator.selectFile(files);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenSelectFileWithSimpleSt1AndSomeGarbageItShouldSelectExpected() {
        File expected = Paths.get("foo.st1").toFile();
        File[] files = new File[]{
                Paths.get("foo.txt").toFile(),
                Paths.get("foo.html").toFile(),
                expected,
                Paths.get("foo.st1_log").toFile(),
        };

        File actual = st1FileLocator.selectFile(files);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenSelectFileWithOriginalAndReacceptedItShouldSelectExpected() {
        File expected = Paths.get("foo_st1.000").toFile();
        File[] files = new File[]{
                Paths.get("foo.st1").toFile(),
                expected
        };

        File actual = st1FileLocator.selectFile(files);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenSelectFileWithOriginalAndSeveralReacceptedItShouldSelectExpected() {
        File expected = Paths.get("foo_st1.004").toFile();
        File[] files = new File[]{
                Paths.get("foo.st1").toFile(),
                Paths.get("foo_st1.000").toFile(),
                expected, // in a funny spot to prove the Comparator works
                Paths.get("foo_st1.001").toFile(),
                Paths.get("foo_st1.003").toFile(),
                Paths.get("foo_st1.002").toFile()
        };

        File actual = st1FileLocator.selectFile(files);

        assertThat(actual).isEqualTo(expected);
    }

}
