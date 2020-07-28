-- alter table
ALTER TABLE book.book_info CHANGE book_id book_id INT AUTO_INCREMENT;
ALTER TABLE book.chapter_info CHANGE chapter_id chapter_id INT AUTO_INCREMENT;
ALTER TABLE book.content_info CHANGE content_id content_id INT AUTO_INCREMENT;
ALTER TABLE book.question_info CHANGE question_id question_id INT AUTO_INCREMENT;
ALTER TABLE book.reference_info CHANGE reference_id reference_id INT AUTO_INCREMENT;
ALTER TABLE book.pic_info CHANGE pic_id pic_id INT AUTO_INCREMENT;
ALTER TABLE book.chat_info CHANGE chat_id chat_id INT AUTO_INCREMENT;
ALTER TABLE book.user_top_map CHANGE rec_id rec_id INT AUTO_INCREMENT;