# Software Requirements Specification (SRS)
## Dự án: Nền tảng Tìm Việc Part-Time

**Phiên bản:** 1.0  
**Ngày:** [Ngày hiện tại]

---

### 1. Giới thiệu
#### 1.1 Mục đích
Tài liệu này mô tả các yêu cầu chức năng và phi chức năng của nền tảng tìm việc part-time theo giờ. Hệ thống sẽ kết nối người lao động và người thuê lao động nhằm hỗ trợ tìm kiếm và thực hiện các công việc ngắn hạn như dọn nhà, chăm sóc cây, chăm sóc thú cưng.

#### 1.2 Phạm vi dự án
Nền tảng bao gồm các chức năng chính:
- Quản lý tài khoản người dùng (đăng nhập, đăng ký, quên mật khẩu).
- Tìm kiếm và đăng công việc.
- Quản lý công việc, đánh giá sau khi hoàn thành.
- Hệ thống thông báo, báo cáo và thanh toán.
- Các chức năng quản lý từ phía Admin.

---

### 2. Mô tả hệ thống
#### 2.1 Môi trường hoạt động
Ứng dụng web tương thích với các trình duyệt phổ biến và thân thiện với thiết bị di động.

#### 2.2 Các bên liên quan
- **Người lao động (Job Seeker):** Tìm kiếm và ứng tuyển công việc.
- **Người thuê lao động (Recruiter):** Đăng tin tuyển dụng và thuê người lao động.
- **Admin:** Quản lý và điều phối hoạt động của hệ thống.

---

### 3. Yêu cầu chức năng
#### 3.1 Chức năng chính của hệ thống
##### 3.1.1 Đăng nhập, Đăng xuất, Đăng ký và Quên Mật Khẩu
- Đăng nhập qua OAuth2: Cho phép người dùng đăng nhập qua Google hoặc sử dụng email và mật khẩu.
- Đăng ký tài khoản: Người dùng tạo tài khoản bằng cách cung cấp email/số điện thoại, kỹ năng, xác thực qua OTP.
- Quên mật khẩu: Đặt lại mật khẩu thông qua OTP gửi qua email/số điện thoại.

##### 3.1.2 Cập Nhật Hồ Sơ Cá Nhân
- Cập nhật thông tin cá nhân: Người dùng có thể cập nhật ảnh đại diện, tên, số điện thoại và kỹ năng.
- Xác thực CCCD: Để đảm bảo tính minh bạch và bảo mật, hệ thống yêu cầu xác thực bằng CCCD.

#### 3.2 Chức năng theo vai trò
**Vai trò: Recruiter (Người thuê lao động)**

##### 3.2.1 Đăng Tin Tuyển Dụng (Job Posting)
- Đăng công việc: Cung cấp tiêu đề, mô tả công việc, thời gian, địa chỉ, hình ảnh, loại công việc (Job Type).
- Trạng thái tin đăng: Các bài đăng sẽ ở trạng thái “Pending” và chờ Admin phê duyệt trước khi công khai.

##### 3.2.2 Quản Lý Job Đã Đăng
- Quản lý công việc: Xem danh sách công việc đã đăng và lọc theo trạng thái (Pending, Approved, Rejected).
- Ứng viên ứng tuyển: Xem danh sách ứng viên đã ứng tuyển, nhận thông báo khi có ứng viên mới.
- Chấp nhận ứng viên: Khi chấp nhận một ứng viên, các ứng viên khác sẽ tự động chuyển sang trạng thái “Rejected”.

##### 3.2.3 Đánh Giá và Nhận Xét
- Đánh giá ứng viên: Sau khi hoàn thành công việc, người thuê có thể đánh giá Job Seeker về thái độ, kỹ năng và chất lượng công việc.

**Vai trò: Job Seeker (Người lao động)**

##### 3.2.4 Ứng Tuyển Job
- Ứng tuyển công việc: Apply vào job và thỏa thuận giá (theo khoảng giá của Job Type) sau khi xác thực CCCD.
- Quản lý ứng tuyển: Xem trạng thái các công việc đã ứng tuyển (Applied, Accepted, Rejected) và nhận thông báo khi trạng thái thay đổi.

##### 3.2.5 Đánh Giá và Nhận Xét
- Đánh giá Recruiter: Sau khi hoàn tất công việc, Job Seeker có thể đánh giá người thuê dựa trên các tiêu chí như tính minh bạch và hỗ trợ.

#### 3.3 Chức năng chung
##### 3.3.1 Báo Cáo
- Chức năng báo cáo: Cả Recruiter và Job Seeker đều có thể báo cáo các hành vi không phù hợp hoặc vi phạm.
- Xử lý báo cáo: Các báo cáo sẽ được gửi đến Admin để xử lý.

##### 3.3.2 Quản Lý Ví và Thanh Toán
- Ví điện tử: Mỗi người dùng có ví điện tử để quản lý tiền.
- Nạp tiền: Người dùng có thể nạp tiền qua ngân hàng, ví điện tử, thẻ tín dụng.
- Thanh toán cho Job Seeker: Sau khi công việc hoàn tất, hệ thống tự động chuyển tiền từ ví của Recruiter sang ví của Job Seeker.
- Lịch sử giao dịch: Người dùng có thể xem lịch sử giao dịch của mình.
- Rút tiền: Job Seeker có thể rút tiền từ ví về tài khoản ngân hàng.

---

### 4. Chức năng quản lý của Admin
#### 4.1 Quản Lý Người Dùng
- Quản lý tài khoản: Xem, khóa hoặc mở khóa tài khoản của người dùng.
- Xác thực CCCD: Xác thực CCCD của người dùng để đảm bảo an toàn và tính minh bạch.

#### 4.2 Quản Lý Tin Tuyển Dụng (Job Management)
- Duyệt tin tuyển dụng: Phê duyệt hoặc từ chối các bài đăng tuyển dụng từ Recruiter.
- Quản lý tin vi phạm: Theo dõi và gỡ bỏ bài đăng khi có vi phạm.

#### 4.3 Quản Lý Đánh Giá và Báo Cáo
- Kiểm duyệt đánh giá: Duyệt các đánh giá để đảm bảo nội dung lành mạnh và không vi phạm.
- Xử lý báo cáo: Xem và giải quyết các báo cáo từ người dùng.

#### 4.4 Quản Lý Ví và Thanh Toán
- Kiểm tra giao dịch: Kiểm tra các giao dịch nạp/rút tiền.
- Xử lý tranh chấp: Giải quyết tranh chấp để đảm bảo công bằng trong thanh toán.

#### 4.5 Quản Lý Thông Báo và Tin Nhắn Hệ Thống
- Gửi thông báo: Gửi thông báo về các thay đổi chính sách hoặc cập nhật hệ thống.
- Hỗ trợ người dùng: Giải đáp các khiếu nại và hỗ trợ người dùng qua tin nhắn hệ thống.

#### 4.6 Quản Lý Nội Dung và Chính Sách
- Quy định và chính sách: Thiết lập và cập nhật các quy định và chính sách sử dụng.
- Kiểm duyệt nội dung: Đảm bảo tuân thủ quy định, bao gồm hình ảnh và các bài viết từ người dùng.

#### 4.7 Quản Lý Báo Cáo và Phân Tích Hệ Thống
- Theo dõi hoạt động: Phân tích báo cáo để đánh giá chất lượng của Recruiter và Job Seeker, cải thiện dịch vụ.

#### 4.8 Quản Lý Tiêu Chí Job (Skill và Job Type)
- Quản lý kỹ năng: Quản lý danh sách kỹ năng cho người dùng chọn.
- Mức giá tham chiếu: Điều chỉnh mức giá tham chiếu theo giờ cho các loại công việc để đảm bảo công bằng và tránh lạm phát giá.

---

### 5. Yêu cầu phi chức năng
#### 5.1 Bảo mật
- Bảo vệ dữ liệu: Mã hóa SSL và bảo mật các giao dịch tài chính.
- Xác thực 2 yếu tố (2FA): Tăng cường bảo mật cho người dùng.

#### 5.2 Hiệu suất
- Thời gian phản hồi nhanh: Tối ưu hóa để thời gian tải trang không quá 3 giây.
- Khả năng xử lý cao: Xử lý tối thiểu 500 yêu cầu đồng thời.

#### 5.3 Khả năng sử dụng
- Giao diện đơn giản: Thân thiện với người dùng ít quen thuộc với công nghệ.
- Hỗ trợ thiết bị di động: Giao diện tương thích tốt trên các thiết bị di động.

---

### 6. Các trường hợp sử dụng chính
Các trường hợp sử dụng chính sẽ bao gồm chi tiết về cách người dùng đăng ký, đăng nhập, đăng tuyển, ứng tuyển công việc, đánh giá và sử dụng ví điện tử.

---

### 7. Phân Chia Task theo Giai Đoạn

#### Giai Đoạn 1: Khởi Động: Phân Tích và Thiết Kế
- **Task 1.1:** Xác định yêu cầu chi tiết (3 ngày, Cao)
- **Task 1.2:** Thiết kế kiến trúc hệ thống (5 ngày, Cao)
- **Task 1.3:** Thiết kế UI/UX (7 ngày, Cao)
- **Task 1.4:** Tài liệu hóa SRS và quy trình hệ thống (3 ngày, Trung bình)

#### Giai Đoạn 2: Phát Triển
...

[Phần chi tiết cho từng task, bao gồm các giai đoạn phát triển, kiểm thử, và triển khai]

---

### 8. Phân Công Nguồn Lực Chính

| Vai trò              | Phân công công việc                                      |
|----------------------|----------------------------------------------------------|
| PM                   | Quản lý dự án, giao tiếp giữa các bên                    |
| BA                   | Phân tích yêu cầu và tài liệu hóa                        |
| System Architect     | Thiết kế kiến trúc hệ thống                              |
| UI/UX Designer       | Thiết kế giao diện người dùng                            |
| Frontend Developer   | Xây dựng các giao diện người dùng                        |
| Backend Developer    | Phát triển API và xử lý logic backend                    |
| Payment Specialist   | Tích hợp thanh toán và quản lý ví điện tử                |
| Admin Panel Specialist | Xây dựng và quản lý bảng điều khiển Admin              |
| QA Engineer          | Kiểm thử chức năng, bảo mật, hiệu suất                   |
| DevOps Engineer      | Triển khai và duy trì hệ thống                          |

---

### 9. Tổng Quan Thời Gian Cần Thiết cho Mỗi Giai Đoạn

Giai đoạn	Tổng thời gian dự kiến	Các ghi chú song song
1. Khởi động (Phân tích và thiết kế)	18 ngày	Một số task thiết kế và tài liệu hóa có thể song song.
2. Phát triển tính năng	32 ngày	Một số task Frontend và Backend có thể song song.
3. Quản lý & Quản trị (Admin)	18 ngày	Các task quản trị có thể song song với phát triển.
4. Kiểm thử	12 ngày	Một số kiểm thử bảo mật và hiệu suất có thể song song.
5. Triển khai	10 ngày	Theo dõi sau triển khai có thể kéo dài thêm.
Tổng Thời Gian Dự Kiến
•	Tổng ngày làm việc: 90 ngày.
Phân Tích Chi Tiết về Tổng Thời Gian
Dưới đây là các yếu tố ảnh hưởng đến tổng thời gian dự án:
1.	Task song song: Một số task thiết kế có thể thực hiện song song với phát triển backend và frontend. Ví dụ, thiết kế UI/UX và phát triển backend có thể tiến hành cùng lúc.


