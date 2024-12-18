USE bsa_monitoring;
-- part 테이블에 다중 행 삽입
INSERT INTO part (name, big_category, small_category, description, part_type)
VALUES
    ('2P6S 배터리 모듈(단위 모듈)', '배터리모듈', 'BMA(2P6S) 모듈', '2P6S 단위 모듈로 구성', '소모품'),
    ('3P4S 배터리 모듈(단위 모듈)', '배터리모듈', 'BMA(3P4S) 모듈', '3P4S 단위 모듈로 구성', '소모품'),
    ('BMS 컨트롤 유닛(Main BMS Unit)', '배터리모듈', 'BMS', '배터리 상태 관리/보호', '반영구품'),
    ('BMS 슬레이브 모듈(BMS Slave Module)', '배터리모듈', 'BMS', '셀 상태 모니터링 서브 유닛', '반영구품'),
    ('온도 센서 모듈(Temperature Sensor Module)', '배터리모듈', '센서 모듈', '온도 모니터링', '반영구품'),
    ('전류 센서 모듈(Current Sensor Module)', '배터리모듈', '센서 모듈', '전류 모니터링', '반영구품'),
    ('BPA 로워 하우징(BPA Lower Housing)', '외부 케이스', 'BPA 케이스', '하단 케이스 보호', '반영구품'),
    ('LWR 어퍼 커버(LWR Upper Cover)', '외부 케이스', 'LWR 케이스', '상단 커버 보호', '반영구품'),
    ('액체형 갭 필러 A(Liquid Gap Filler A)', '외부 케이스', '갭 필러', '열 전달용 A타입', '소모품'),
    ('액체형 갭 필러 B(Liquid Gap Filler B)', '외부 케이스', '갭 필러', '열 전달용 B타입', '소모품'),
    ('HV 메인 와이어 하네스(HV Main Wiring)', '전기 배선', '고전압 전선', '고전압 전력 전송', '소모품'),
    ('LV 와이어 하네스(LV Wiring Harness)', '전기 배선', '저전압 전선', '제어신호/통신용', '소모품'),
    ('HV 와이어 클립(HV Wire Clip)', '전기 배선', '배선 고정 클립', 'HV 전선 고정', '반영구품'),
    ('LV 와이어 클립(LV Wire Clip)', '전기 배선', '배선 고정 클립', 'LV 전선 고정', '반영구품'),
    ('HV 절연 커버(HV Insulation Cover)', '전기 배선', '절연 커버', '고전압부 절연', '소모품'),
    ('LV 절연 커버(LV Insulation Cover)', '전기 배선', '절연 커버', '저전압부 절연', '소모품'),
    ('열 전도 패드(Thermal Pad)', '열 관리', '방열 패드', '발열 분산', '소모품'),
    ('어퍼 케이스(Upper Case)', '열 관리', '상단 케이스', '최상단 보호/밀폐', '반영구품'),
    ('쿨링 플레이트(Cooling Plate)', '열 관리', '쿨링 플레이트', '셀 내부 발열 외부로 전달', '반영구품'),
    ('냉각 파이프(Cooling Pipe)', '열 관리', '쿨링 시스템', '냉각수 흐름 경로', '반영구품'),
    ('냉각 매니폴드(Cooling Manifold)', '열 관리', '쿨링 시스템', '냉각수 분배', '반영구품'),
    ('펌프(Cooling Pump)', '열 관리', '쿨링 시스템', '냉각수 순환 장치', '반영구품'),
    ('양극 버스바(Positive Bus Bar)', '모듈 연결 부품', 'Bus Bar', '양극 연결 담당', '소모품'),
    ('음극 버스바(Negative Bus Bar)', '모듈 연결 부품', 'Bus Bar', '음극 연결 담당', '소모품'),
    ('커넥터 단자(Connector Terminal)', '모듈 연결 부품', '접촉 단자', '모듈 간 전류 흐름', '반영구품'),
    ('셀 스페이서(Cell Spacer)', '구조적 안정화 부품', '셀 간 Spacer', '셀 간격 유지/충격 완화', '소모품'),
    ('마운팅 브래킷(Mounting Bracket)', '구조적 안정화 부품', '고정 브래킷', '셀 모듈 고정', '반영구품'),
    ('HV 퓨즈(HV Fuse)', '안전 장치', '퓨즈', '단락 시 보호', '반영구품'),
    ('HV 릴레이(HV Relay)', '안전 장치', '릴레이', '전류 제어/차단', '반영구품'),
    ('DC/DC 컨버터(DC/DC Converter)', '전원 연결', 'DC-DC 변환기', '전압 변환', '반영구품'),
    ('압력 센서(Pressure Sensor)', '보조 센서', '압력 센서', '내부 압력 변화 감지', '반영구품'),
    ('습도 센서(Humidity Sensor)', '보조 센서', '습도 센서', '내부 습도 관리', '반영구품'),
    ('CAN 통신 모듈', '신호 및 통신', 'CAN 통신 모듈', '배터리 팩의 상태를 차량 시스템과 통합', '반영구품'),
    ('BLE 모듈', '신호 및 통신', 'BLE 모듈', '무선 통신 지원', '반영구품');

-- production_line 테이블 삽입
INSERT INTO production_line (production_line_id, daily_target, monthly_target, production_start_time, production_end_time, status, product)
VALUES
    (1, 1000, 30000, NOW(), NULL, 'STOPPED', '2P144S'),
    (2, 800, 24000, NOW(), NULL, 'STOPPED', '2P180S'),
    (3, 750, 22500, NOW(), NULL, 'STOPPED', '2P192S'),
    (4, 900, 27000, NOW(), NULL, 'STOPPED', '3P144S');
-- process_part 테이블에 데이터 삽입
INSERT INTO process_part (process_id, part_id, last_warehousing_date, current_quantity, minimum_required_quantity)
SELECT
    p.process_id,
    pt.part_id,
    NOW() as last_warehousing_date,
    100 as current_quantity,
    CASE
        -- BMA 모듈류는 더 많은 수량이 필요
        WHEN pt.small_category IN ('BMA(2P6S) 모듈', 'BMA(3P4S) 모듈') THEN 50
        -- 배터리 모듈 관련 부품들
        WHEN pt.big_category = '배터리모듈' THEN 20
        -- 그 외 부품들
        ELSE 10
        END as minimum_required_quantity
FROM process p
         CROSS JOIN part pt
WHERE
   -- BMA 로딩 공정에 필요한 부품
    (p.name = 'BMA로딩' AND pt.big_category = '배터리모듈') OR
   -- BPA 조립 공정에 필요한 부품
    (p.name = 'BPA조립' AND pt.big_category IN ('외부 케이스', '열 관리')) OR
   -- 전장품 조립 공정에 필요한 부품
    (p.name = '전장품조립' AND pt.big_category IN ('전기 배선', '안전 장치', '전원 연결')) OR
   -- W/H-버스바 조립 공정에 필요한 부품
    (p.name = 'W/H-버스바조립' AND pt.big_category = '모듈 연결 부품');
-- process 테이블 삽입
INSERT INTO process (process_id, name)
VALUES
    (1, 'BMA로딩'),
    (2, 'BPA조립'),
    (3, 'LWRCase공급'),
    (4, '갭필러도포'),
    (5, 'BPA로딩'),
    (6, 'BMA가체결'),
    (7, 'BMA자동체결'),
    (8, '갭필러건조'),
    (9, '전장품조립'),
    (10, 'W/H-버스바조립'),
    (11, '고전압커넥터'),
    (12, 'EOL검사'),
    (13, 'U/Case조립'),
    (14, '냉각수기밀검사'),
    (15, '팩기밀/유량검사'),
    (16, 'EOL검사-바코드부착');

-- production_line_process 테이블 삽입
INSERT INTO production_line_process (production_line_id, process_id, base_exec_time, status)
VALUES
-- 생산라인 1 (2P144S)
(1, 1, 5, 'STOPPED'),
(1, 2, 6, 'STOPPED'),
(1, 3, 4, 'STOPPED'),
(1, 4, 7, 'STOPPED'),
(1, 5, 5, 'STOPPED'),
(1, 6, 6, 'STOPPED'),
(1, 7, 6, 'STOPPED'),
(1, 8, 7, 'STOPPED'),
(1, 9, 6, 'STOPPED'),
(1, 10, 5, 'STOPPED'),
(1, 11, 5, 'STOPPED'),
(1, 12, 5, 'STOPPED'),
(1, 13, 4, 'STOPPED'),
(1, 14, 6, 'STOPPED'),
(1, 15, 6, 'STOPPED'),
(1, 16, 5, 'STOPPED'),
-- 생산라인 2 (2P180S)
(2, 1, 6, 'STOPPED'),
(2, 2, 7, 'STOPPED'),
(2, 3, 5, 'STOPPED'),
(2, 4, 9, 'STOPPED'),
(2, 5, 6, 'STOPPED'),
(2, 6, 8, 'STOPPED'),
(2, 7, 8, 'STOPPED'),
(2, 8, 9, 'STOPPED'),
(2, 9, 7, 'STOPPED'),
(2, 10, 6, 'STOPPED'),
(2, 11, 6, 'STOPPED'),
(2, 12, 6, 'STOPPED'),
(2, 13, 5, 'STOPPED'),
(2, 14, 8, 'STOPPED'),
(2, 15, 8, 'STOPPED'),
(2, 16, 6, 'STOPPED'),
-- 생산라인 3 (2P192S)
(3, 1, 7, 'STOPPED'),
(3, 2, 7, 'STOPPED'),
(3, 3, 5, 'STOPPED'),
(3, 4, 9, 'STOPPED'),
(3, 5, 6, 'STOPPED'),
(3, 6, 8, 'STOPPED'),
(3, 7, 9, 'STOPPED'),
(3, 8, 9, 'STOPPED'),
(3, 9, 7, 'STOPPED'),
(3, 10, 7, 'STOPPED'),
(3, 11, 6, 'STOPPED'),
(3, 12, 6, 'STOPPED'),
(3, 13, 6, 'STOPPED'),
(3, 14, 8, 'STOPPED'),
(3, 15, 9, 'STOPPED'),
(3, 16, 6, 'STOPPED'),
-- 생산라인 4 (3P144S)
(4, 1, 5, 'STOPPED'),
(4, 2, 6, 'STOPPED'),
(4, 3, 4, 'STOPPED'),
(4, 4, 8, 'STOPPED'),
(4, 5, 5, 'STOPPED'),
(4, 6, 7, 'STOPPED'),
(4, 7, 7, 'STOPPED'),
(4, 8, 8, 'STOPPED'),
(4, 9, 6, 'STOPPED'),
(4, 10, 6, 'STOPPED'),
(4, 11, 5, 'STOPPED'),
(4, 12, 5, 'STOPPED'),
(4, 13, 5, 'STOPPED'),
(4, 14, 7, 'STOPPED'),
(4, 15, 7, 'STOPPED'),
(4, 16, 5, 'STOPPED');