//package com.cg.ucaa.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotSame;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.cg.ucaa.entities.LoginEntity;
//import com.cg.ucaa.exception.LoginFailedException;
//import com.cg.ucaa.models.LoginModel;
//import com.cg.ucaa.repository.ILoginRepository;
//import com.cg.ucaa.service.LoginServiceImpl;
//
///**
// * Test Class
// * @author Akshat Kumar
// *
// */
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class LoginServiceImplTests {
//
//	/* mocking the repository */
//	@Mock
//	private ILoginRepository repo;
//
//	/*
//	 * injecting Login repository marked as @Mock into
//	 * Login service implementation
//	 */
//	@InjectMocks
//	private LoginServiceImpl usmImpl;
//	
//	
//	/**
//	 * Test Case 1 - validating login details of a specific staff related to the university
//	 */
//	@Test
//	@DisplayName("LoginServiceImpl::viewStaff should return existing staff login as LoginModel is provided with specific id for each staff member")
//	void testViewStaff() throws LoginFailedException {
//
//		LoginEntity testData = new LoginEntity(1L, "akshat", "pass12");
//		LoginModel  expected = new LoginModel(1L, "akshat", "pass12");
//
//		Mockito.when(repo.findById(testData.getLoginId())).thenReturn(Optional.of(testData));
//		
//		String actual = usmImpl.loginAsUniversityStaffMember(expected);
//		assertEquals("Login Successfull! akshat Welcome", actual);
//	}
//	
//	/**
//	 * Test Case 2 - validating not same for wrong login details of a specific staff related to the university
//	 */
//	@Test
//	@DisplayName("LoginServiceImpl::viewStaff2 should not return existing staff login")
//	void testViewStaff2() throws LoginFailedException {
//
//		LoginEntity testData = new LoginEntity(1L, "akshat", "pass12");
//		LoginModel  expected = new LoginModel(1L, "aks", "pass12");
//
//		Mockito.when(repo.findById(testData.getLoginId())).thenReturn(Optional.of(testData));
//		
//		String actual = usmImpl.loginAsUniversityStaffMember(expected);
//		assertNotSame("Login Successfull! akshat Welcome", actual);
//	}
//	
//	/**
//	 * Test Case 3 - validating login details of a specific member related to the Admission Committee
//	 */
//	@Test
//	@DisplayName("LoginServiceImpl::viewCommitteeMember should return existing member login as LoginModel is provided with specific id for each committee member")
//	void testViewCommitteeMember() throws LoginFailedException {
//
//		LoginEntity testData = new LoginEntity(1L, "akshat", "pass12");
//		LoginModel  expected = new LoginModel(1L, "akshat", "pass12");
//
//		Mockito.when(repo.findById(testData.getLoginId())).thenReturn(Optional.of(testData));
//		
//		String actual = usmImpl.loginAsAdmissionCommiteeMember(expected);
//		assertEquals("Login Successfull! akshat Welcome", actual);
//	}
//	
//	/**
//	 * Test Case 4 - validating not same for wrong login details of a specific member related to the Admission Committee
//	 */
//	@Test
//	@DisplayName("LoginServiceImpl::viewCommitteeMember2 should return existing staff login")
//	void testViewCommitteeMember2() throws LoginFailedException {
//
//		LoginEntity testData = new LoginEntity(1L, "akshat", "pass12");
//		LoginModel  expected = new LoginModel(1L, "aks", "pass12");
//
//		Mockito.when(repo.findById(testData.getLoginId())).thenReturn(Optional.of(testData));
//		
//		String actual = usmImpl.loginAsUniversityStaffMember(expected);
//		assertNotSame("Login Successfull! akshat Welcome", actual);
//	}
//	
//	/**
//	 * Test Case 5 - validating login details of a specific applicant
//	 */
//	@Test
//	@DisplayName("LoginServiceImpl::viewApplicant should return existing applicant login as LoginModel is provided with specific id for each applicant")
//	void testViewApplicant() throws LoginFailedException {
//
//		LoginEntity testData = new LoginEntity(1L, "akshat", "pass12");
//		LoginModel  expected = new LoginModel(1L, "aks", "pass12");
//
//		Mockito.when(repo.findById(testData.getLoginId())).thenReturn(Optional.of(testData));
//		
//		String actual = usmImpl.loginAsUniversityStaffMember(expected);
//		assertEquals("Login Successfull! akshat Welcome", actual);
//	}
//	
//	/**
//	 * Test Case 6 - validating not same for wrong login details of a specific applicant
//	 */
//	@Test
//	@DisplayName("LoginServiceImpl::viewApplicant2 should return existing staff login")
//	void testViewApplicant2() throws LoginFailedException {
//
//		LoginEntity testData = new LoginEntity(1L, "akshat", "pass12");
//		LoginModel  expected = new LoginModel(1L, "aks", "pass12");
//
//		Mockito.when(repo.findById(testData.getLoginId())).thenReturn(Optional.of(testData));
//		
//		String actual = usmImpl.loginAsUniversityStaffMember(expected);
//		assertNotSame("Login Successfull! akshat Welcome", actual);
//	}
//	
//	
//}
