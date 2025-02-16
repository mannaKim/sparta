package com.example.board.service;

import com.example.board.dto.member.MemberResponseDto;
import com.example.board.dto.member.SignUpResponseDto;
import com.example.board.entity.Member;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {    // 확장될 가능성이 없다면 Interface가 아닌 구현체를 의존해도 된다.

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String userName, String password, Integer age) {

        Member member = new Member(userName, password, age);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(
                savedMember.getId(),
                savedMember.getUserName(),
                savedMember.getAge());
    }

    public MemberResponseDto findById(Long id) {

        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dose not exist id: " + id);
        }

        Member findMember = optionalMember.get();

        return new MemberResponseDto(
                findMember.getUserName(),
                findMember.getAge()
        );
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if (!findMember.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findMember.updatePassword(newPassword);
    }
}
