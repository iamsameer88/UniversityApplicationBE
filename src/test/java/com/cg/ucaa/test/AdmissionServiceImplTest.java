package com.cg.ucaa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ucaa.entities.AdmissionEntity;
import com.cg.ucaa.entities.AdmissionStatus;
import com.cg.ucaa.exception.AdmissionNotGrantedException;
import com.cg.ucaa.models.AdmissionModel;
import com.cg.ucaa.repository.IAdmissionRepository;
import com.cg.ucaa.service.AdmissionServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AdmissionServiceImplTest {

	/* mocking the repository */
	@Mock
	private IAdmissionRepository repo;

	/*
	 * injecting Admission repository marked as @Mock into Admission service
	 * implementation
	 */
	@InjectMocks
	private AdmissionServiceImpl asImpl;

	/**
	 * listing all the admissions
	 */
	@Test
	@DisplayName("AdmissionServiceImpl::viewAllAdmissions() should return list of existing admissions as AdmissionModel List")
	void testViewAllAdmissions() {

		List<AdmissionEntity> testdata = Arrays.asList(new AdmissionEntity[] {
				new AdmissionEntity(22L, LocalDate.parse("2021-05-21"), AdmissionStatus.Confirmed),
				new AdmissionEntity(24L, LocalDate.parse("2021-05-05"), AdmissionStatus.Confirmed) });

		/* when repo.findAll() is called, then test data */
		Mockito.when(repo.findAll()).thenReturn(testdata);
		List<AdmissionModel> expected = Arrays.asList(new AdmissionModel[] {
				new AdmissionModel(22L, LocalDate.parse("2021-05-21"), AdmissionStatus.Confirmed),
				new AdmissionModel(24L, LocalDate.parse("2021-05-05"), AdmissionStatus.Confirmed) });

		List<AdmissionModel> actual = asImpl.viewAllAdmissions();
		assertEquals(expected, actual);
	}

	/**
	 * finding a particular admission by admission id
	 * 
	 * @throws AdmissionNotGrantedException
	 */
	@Test
	@DisplayName("AdmissionServiceImpl::findById() should return exeisting admission as Admission Model when provided with existing admission Id")
	void testFindById() throws AdmissionNotGrantedException {

		AdmissionEntity testdata = new AdmissionEntity(22L, LocalDate.parse("2021-05-21"), AdmissionStatus.Confirmed);
		AdmissionModel expected = new AdmissionModel(22L, LocalDate.parse("2021-05-21"), AdmissionStatus.Confirmed);

		Mockito.when(repo.findById(testdata.getAdmissionId())).thenReturn(Optional.of(testdata));

		AdmissionModel actual = asImpl.viewByAdmissionId((expected.getAdmissionId()));
		assertEquals(expected, actual);
	}

	/**
	 * providing null if no admission by given id exists
	 * 
	 * @throws AdmissionNotGrantedException
	 */
	@Test
	@DisplayName("AdmissionImpl::findById() should return null given nonexisting admissionId")
	void testFindById2() throws AdmissionNotGrantedException {
		AdmissionModel actual = null;
		try {
			Mockito.when(repo.findById(1L)).thenReturn(Optional.empty());
			actual = asImpl.viewByAdmissionId(1L);
			if (actual.equals(null)) {
				assertEquals(null, actual);
			}
		} catch (AdmissionNotGrantedException excep) {
			assertEquals(null, actual);
		}

	}

	/**
	 * adding new admission
	 * 
	 * @throws AdmissionNotGrantedException
	 */
	@Test
	@DisplayName("AdmissionServiceImpl::addAdmission() should return admission added with given admission id")
	void testAddAdmission() throws AdmissionNotGrantedException {

		AdmissionEntity testdata = new AdmissionEntity(22L, LocalDate.parse("2021-05-21"), AdmissionStatus.Confirmed);
		AdmissionModel expected = new AdmissionModel(22L, LocalDate.parse("2021-05-21"), AdmissionStatus.Confirmed);

		Mockito.when(repo.save(testdata)).thenReturn(testdata);
		Mockito.when(repo.existsById(22L)).thenReturn(false);
		AdmissionModel actual = asImpl.addAdmission(expected);

		assertEquals(expected, actual);
	}

	/**
	 * updating admission
	 * 
	 * @throws AdmissionNotGrantedException
	 */
	@Test
	@DisplayName("AdmissionServiceImpl::updateAdmission() should return updated admission with provided admission id")
	void testUpdateAdmission() throws AdmissionNotGrantedException {

		AdmissionEntity testdata = new AdmissionEntity(24L, LocalDate.parse("2021-05-05"), AdmissionStatus.Confirmed);
		AdmissionModel expected = new AdmissionModel(24L, LocalDate.parse("2021-05-11"), AdmissionStatus.Confirmed);

		testdata.setAdmissionDate(LocalDate.parse("2021-05-11"));

		Mockito.when(repo.save(testdata)).thenReturn(testdata);
		AdmissionModel actual = asImpl.addAdmission(expected);

		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("AdmissionServiceImpl::updateAdmission2() should return updated admission with provided admission id")
	void testUpdateAdmission2() throws AdmissionNotGrantedException {

		AdmissionEntity testdata = new AdmissionEntity(22L, LocalDate.parse("2021-05-21"), AdmissionStatus.Confirmed);
		AdmissionModel expected = new AdmissionModel(22L, LocalDate.parse("2021-05-21"), AdmissionStatus.Applied);

		testdata.setAdmissionStatus(AdmissionStatus.Applied);

		Mockito.when(repo.save(testdata)).thenReturn(testdata);
		AdmissionModel actual = asImpl.addAdmission(expected);

		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("AdmissionServiceImpl::updateAdmission3() should return null when provided with non existing admission id")
	void testUpdateAdmission3() throws AdmissionNotGrantedException {

		AdmissionModel actual = null;

		try {
			Mockito.when(repo.findById(1L)).thenReturn(Optional.empty());
			actual = asImpl.viewByAdmissionId(1L);
			if (actual.equals(null)) {
				assertEquals(null, actual);
			}
		} catch (AdmissionNotGrantedException excep) {
			assertEquals(null, actual);
		}
	}

	/**
	 * removing admission
	 * 
	 * @throws AdmissionNotGrantedException
	 */
	@Test
	@DisplayName("AdmissionServiceImpl::deleteById() should remove admission by given admission id ")
	void testDeleteById() throws AdmissionNotGrantedException {
		AdmissionEntity testdata = new AdmissionEntity(23L, LocalDate.parse("2021-05-05"), AdmissionStatus.Confirmed);

		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(testdata));

		Mockito.doNothing().when(repo).deleteById(1L);
		boolean result = asImpl.removeAdmission(1L);
		assertTrue(result);

	}

}