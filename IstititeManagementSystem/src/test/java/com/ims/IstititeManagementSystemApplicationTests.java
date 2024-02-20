package com.ims;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ims.entity.Institute;
import com.ims.exception.InstituteManagementSystemException;
import com.ims.repository.InstituteRepository;
import com.ims.service.InstituteServiceImpl;

@SpringBootTest
class IstititeManagementSystemApplicationTests {

	@Test
	void contextLoads() {
		
	}
	@Mock
    private InstituteRepository instituteRepository;

    @InjectMocks
    private InstituteServiceImpl instituteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterInstitute() {
        // Given
        Institute institute = new Institute();
        institute.setName("Test Institute");

        when(instituteRepository.save(institute)).thenReturn(institute);

        // When
        Institute registeredInstitute = instituteService.registerInstitute(institute);

        // Then
        assertThat(registeredInstitute).isNotNull();
        assertThat(registeredInstitute.getName()).isEqualTo("Test Institute");
    }

    @Test
    public void testUpdateInstitute() {
        // Given
        int id = 1;
        Institute existingInstitute = new Institute();
        existingInstitute.setId(id);
        existingInstitute.setName("Existing Institute");

        Institute updatedInstitute = new Institute();
        updatedInstitute.setName("Updated Institute");

        when(instituteRepository.findById(id)).thenReturn(Optional.of(existingInstitute));
        when(instituteRepository.save(existingInstitute)).thenReturn(existingInstitute);

        // When
        Institute result = instituteService.updateInstitute(id, updatedInstitute);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getName()).isEqualTo("Updated Institute");
    }

    @Test
    public void testGetInstituteById() {
        // Given
        int id = 1;
        Institute institute = new Institute();
        institute.setId(id);
        institute.setName("Test Institute");

        when(instituteRepository.findById(id)).thenReturn(Optional.of(institute));

        // When
        Institute result = instituteService.getInstituteById(id);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getName()).isEqualTo("Test Institute");
    }

    @Test
    public void testGetInstituteById_InstituteNotFound() {
        // Given
        int id = 1;
        when(instituteRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> instituteService.getInstituteById(id))
                .isInstanceOf(InstituteManagementSystemException.class)
                .hasMessageContaining("Institute not found with id: " + id);
    }

}
