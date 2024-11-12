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
- **Đăng nhập, Đăng xuất, Đăng ký và Quên Mật Khẩu**
  - Đăng nhập qua OAuth2: Cho phép người dùng đăng nhập qua Google hoặc sử dụng email và mật khẩu.
  - Đăng ký tài khoản: Người dùng tạo tài khoản bằng cách cung cấp email/số điện thoại, kỹ năng, xác thực qua OTP.
  - Quên mật khẩu: Đặt lại mật khẩu thông qua OTP gửi qua email/số điện thoại.

- **Cập Nhật Hồ Sơ Cá Nhân**
  - Cập nhật thông tin cá nhân: Người dùng có thể cập nhật ảnh đại diện, tên, số điện thoại và kỹ năng.
  - Xác thực CCCD: Để đảm bảo tính minh bạch và bảo mật, hệ thống yêu cầu xác thực bằng CCCD.

---

### 4. Chức năng quản lý của Admin
- Quản lý tài khoản: Xem, khóa hoặc mở khóa tài khoản của người dùng.
- Xác thực CCCD: Xác thực CCCD của người dùng để đảm bảo an toàn và tính minh bạch.
- Duyệt tin tuyển dụng: Phê duyệt hoặc từ chối các bài đăng tuyển dụng từ Recruiter.
- Quản lý tin vi phạm: Theo dõi và gỡ bỏ bài đăng khi có vi phạm.

---

### 5. Yêu cầu phi chức năng
- Bảo vệ dữ liệu: Mã hóa SSL và bảo mật các giao dịch tài chính.
- Xác thực 2 yếu tố (2FA): Tăng cường bảo mật cho người dùng.
- Thời gian phản hồi nhanh: Tối ưu hóa để thời gian tải trang không quá 3 giây.
- Khả năng xử lý cao: Xử lý tối thiểu 500 yêu cầu đồng thời.

---

### 6. Các trường hợp sử dụng chính
Các trường hợp sử dụng chính sẽ bao gồm chi tiết về cách người dùng đăng ký, đăng nhập, đăng tuyển, ứng tuyển công việc, đánh giá và sử dụng ví điện tử.

---

### 7. Phân Chia Task theo Giai Đoạn
- **Giai Đoạn 1: Khởi Động**
  - Phân tích và thiết kế yêu cầu chi tiết, thiết kế hệ thống và UI/UX.
- **Giai Đoạn 2: Phát Triển**
  - Thực hiện phát triển các chức năng đăng nhập, đăng ký, quản lý công việc và thanh toán.
- **Giai Đoạn 3: Kiểm Thử**
  - Kiểm thử chức năng và bảo mật, kiểm tra khả năng xử lý và toàn vẹn dữ liệu.
- **Giai Đoạn 4: Triển Khai**
  - Triển khai hệ thống trên môi trường sản xuất và tối ưu sau triển khai.

---

### 8. Phân Công Nguồn Lực Chính
- **PM:** Quản lý dự án và giao tiếp giữa các bên.
- **BA:** Phân tích yêu cầu và tài liệu hóa.
- **System Architect:** Thiết kế kiến trúc hệ thống.
- **UI/UX Designer:** Thiết kế giao diện người dùng.

---

### 9. Tổng Quan Thời Gian
Tổng thời gian làm việc dự kiến: 90 ngày với các nhiệm vụ song song để tối ưu tiến độ.
