package com.learningcrew.linkup.linker.command.domain.service;

import com.learningcrew.linkup.common.domain.Role;
import com.learningcrew.linkup.common.domain.Status;
import com.learningcrew.linkup.common.dto.query.RoleDTO;
import com.learningcrew.linkup.common.dto.query.StatusDTO;
import com.learningcrew.linkup.common.query.mapper.RoleMapper;
import com.learningcrew.linkup.common.query.mapper.StatusMapper;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;

import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.constants.LinkerStatusType;
import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final StatusMapper statusMapper;
    private final RoleMapper roleMapper;

    /* 비밀번호 암호화 */
    @Transactional
    public void encryptPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    /* 권한 설정 */
    @Transactional
    public void assignRole(User user, String roleName) {
        RoleDTO roleDTO = roleMapper.roleByRoleName(roleName).orElseThrow(
                () -> new BusinessException(ErrorCode.INVALID_ROLE)
        );
        Role roleEntity = Role.of(roleDTO.getRoleId(),roleDTO.getRoleName());
        user.setRole(roleEntity);
    }

    /* 초기 상태 설정 */
    @Transactional
    public void assignStatus(User user, String statusType) {
        StatusDTO statusDTO = statusMapper.statusByStatusType(statusType).orElseThrow(
                ()-> new BusinessException(ErrorCode.INVALID_STATUS)
        );
        Status status = Status.of(statusDTO.getStatusId(),statusDTO.getStatusType());
        user.setStatus(status);
    }

    /* 회원 삽입 */
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /* 이메일 유무 확인 */
    public boolean existsByUserEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /* 활성화 상태 처리 */
    @Transactional
    public void activateUser(User user) {
        StatusDTO statusDTO = statusMapper.statusByStatusType(LinkerStatusType.ACCEPTED.name()).orElseThrow(
                ()-> new BusinessException(ErrorCode.INVALID_STATUS)
        );
        Status activeStatus = Status.of(statusDTO.getStatusId(),statusDTO.getStatusType());
        user.setStatus(activeStatus);
        userRepository.save(user);
    }
}
