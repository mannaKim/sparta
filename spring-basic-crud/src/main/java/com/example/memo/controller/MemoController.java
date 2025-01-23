package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos")
public class MemoController {
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {

        // 식별자가 1씩 증가하도록 만들어야함
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // 요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());

        // Inmemory DB에 Memo 저장
        memoList.put(memoId, memo);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> findAllMemos() {

        // init List
        List<MemoResponseDto> responseList = new ArrayList<>();

        // HashMap<Memo> -> List<MemoResponseDto>
        // Map To List 1번째 방법
//        for (Memo memo : memoList.values()) {
//            MemoResponseDto responseDto = new MemoResponseDto(memo);
//            responseList.add(responseDto);
//        }

        // Map To List 2번째 방법
        responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

        if (responseList.isEmpty()) {
            // 요청은 성공했으나, 응답 본문 없음\
            // ResponseEntity는 제네릭 타입과 관계없이 body를 생략하거나 null로 처리하는 것을 허용
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // 이 if문이 없다면, responseList가 비어있을 때 200OK와 빈 배열을 반환한다.
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {

        Memo memo = memoList.get(id);
        
        if (memo == null) {
            // 조회된 메모가 없을 때 404 not found 반환
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemoById(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ) {
        Memo memo = memoList.get(id);

        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 필수값 검증: 제목과 내용
        if (dto.getTitle() == null || dto.getContents() == null) {
            // 필수값이 없을 때 400 bad request 반환
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        memo.update(dto);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
        @PathVariable Long id,
        @RequestBody MemoRequestDto dto
    ) {
        Memo memo = memoList.get(id);

        // NPE 방지
        if (memo == null) {
            // 조회된 메모가 없을 때 404 not found 반환
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 필수값 검증: 제목 (내용은 없어야만 함)
        if (dto.getTitle() == null || dto.getContents() != null) {
            // 필수값이 없을 때 400 bad request 반환
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.updateTitle(dto);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable long id) {

        // memoList의 Key값에 id를 포함하고 있다면 삭제
        if (memoList.containsKey(id)) {
            memoList.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
