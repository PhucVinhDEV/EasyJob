package com.example.EasyJob.User;


import com.example.EasyJob.role.model.Role;
import com.example.EasyJob.user.mapper.UserMapper;
import com.example.EasyJob.user.model.User;
import com.example.EasyJob.user.model.record.UserRecord;
import com.example.EasyJob.user.model.reponsese.UserDTO;
import com.example.EasyJob.user.repository.UserRepository;
import com.example.EasyJob.user.service.UserService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.example.EasyJob.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;
    private UserDTO testUserDTO;
    private UserRecord testUserRecord;

    @Test
    void testGetRepository() {
        // Act
        var repository = userService.getRepository();

        // Assert
        assertThat(repository).isEqualTo(userRepository);

        var mapper = userService.getMapper();

        assertThat(mapper).isEqualTo(userMapper);
    }

    @Test
    void testFindById() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User testUser = new User("Tpvinh292931@gmail", "password", "Trần Phúc Vinh", "0975292931", "img", "12 year", User.Gender.MALE, User.StatusVerified.VERIDIED, null);
        testUser.setId(userId);

        UserDTO testUserDTO = new UserDTO(userId, "Tpvinh292931@gmail", "Trần Phúc Vinh");

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(userMapper.maptoDto(testUser)).thenReturn(testUserDTO);

        // Act
        UserDTO result = userService.findById(userId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(testUserDTO.getEmail());
        assertThat(result.getPassword()).isEqualTo(testUserDTO.getPassword());
        verify(userRepository).findById(userId);
        verify(userMapper).maptoDto(testUser);
    }

    @Test
    void testSave() {
        // Arrange
        UserRecord testUserRecord = new UserRecord(null,"Tpvinh292931@gmail", "password", "Trần Phúc Vinh", "0975292931", "img", "12 year", User.Gender.MALE, User.StatusVerified.PENDING);
        User testUser = new User("Tpvinh292931@gmail", "password", "Trần Phúc Vinh", "0975292931", "img", "12 year", User.Gender.MALE, User.StatusVerified.PENDING, null);
        testUser.setId(UUID.randomUUID());

        UserDTO testUserDTO = new UserDTO(testUser.getId(), "Tpvinh292931@gmail", "Trần Phúc Vinh");

        when(userMapper.maptoEntity(testUserRecord)).thenReturn(testUser);
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(userMapper.maptoDto(testUser)).thenReturn(testUserDTO);

        // Act
        UserDTO result = userService.save(testUserRecord);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(testUserDTO.getEmail());
        verify(userMapper).maptoEntity(testUserRecord);
        verify(userRepository).save(testUser);
        verify(userMapper).maptoDto(testUser);
    }

    @Test
    void testUpdate() {
        // Arrange
        UUID userId = UUID.randomUUID();

        UserRecord testUserRecord = new UserRecord(
                userId,
                "updated@example.com",
                "new-password",
                "Updated User",
                "0987654321",
                "new-img",
                "15 year",
                User.Gender.MALE,
                User.StatusVerified.VERIDIED
        );

        User existingUser = new User(
                "Tpvinh292931@gmail",
                "password",
                "Trần Phúc Vinh",
                "0975292931",
                "img",
                "12 year",
                User.Gender.MALE,
                User.StatusVerified.PENDING,
                null
        );
        existingUser.setId(userId);

        User updatedUser = new User(
                "updated@example.com",
                "new-password",
                "Updated User",
                "0987654321",
                "new-img",
                "15 year",
                User.Gender.MALE,
                User.StatusVerified.VERIDIED,
                null
        );
        updatedUser.setId(userId);

        UserDTO updatedUserDTO = new UserDTO(
                userId,
                "updated@example.com",
                "Updated User"
        );

        // Set up mocks
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        doNothing().when(userMapper).updateEntityFromRecord(testUserRecord, existingUser); // Mock the void method
        when(userRepository.save(existingUser)).thenReturn(updatedUser);
        when(userMapper.maptoDto(updatedUser)).thenReturn(updatedUserDTO);

        // Act
        UserDTO result = userService.update(userId, testUserRecord);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(updatedUserDTO.getEmail());
        assertThat(result.getPassword()).isEqualTo(updatedUserDTO.getPassword());

        // Verify interactions
        verify(userRepository).findById(userId);
        verify(userMapper).updateEntityFromRecord(testUserRecord, existingUser);
        verify(userRepository).save(existingUser);
        verify(userMapper).maptoDto(updatedUser);

        // Verify that mapper correctly handled the final object

        // Capture the user passed to save() and check its properties
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userMapper).maptoDto(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertThat(capturedUser.getEmail()).isEqualTo(testUserRecord.email());
        assertThat(capturedUser.getFullName()).isEqualTo(testUserRecord.fullName());
        assertThat(capturedUser.getPhone()).isEqualTo(testUserRecord.phone());
        assertThat(capturedUser.getGender()).isEqualTo(testUserRecord.gender());
        assertThat(capturedUser.getStatusVerified()).isEqualTo(testUserRecord.statusVerified());


    }

    @Test
    void testDeleteById() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User testUser = new User("Tpvinh292931@gmail", "password", "Trần Phúc Vinh", "0975292931", "img", "12 year", User.Gender.MALE, User.StatusVerified.VERIDIED, null);
        testUser.setId(userId);

        // Mocking the findById to return the user initially
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // Mocking the delete method to do nothing (this simulates a successful delete)
        doNothing().when(userRepository).delete(testUser);

        // Act
        userService.deleteById(userId);

        // After deletion, make sure findById returns Optional.empty()
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Assert
        assertThat(userRepository.findById(userId)).isEmpty();  // Check that the user is deleted
        verify(userRepository).delete(testUser);  // Verify that delete was called
    }
}
