insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000001, '2023-05-12', 01,
               '2023-05-12', 'Email', 'ЦБ',
               'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
                   true, false, '/uploads/0000001/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000002, '2024-06-12', 02,
        '2024-09-09', 'Email', 'ЦБ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000002/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000003, '2024-07-12', 03,
        '2024-09-09', 'Email', 'ГНИ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000003/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000004, '2024-08-12', 04,
        '2024-09-09', 'Email', 'ТСЖ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000004/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000005, CURRENT_DATE, 05,
        CURRENT_DATE, 'Курьер', 'ЦБ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000005/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000006, CURRENT_DATE, 06,
        CURRENT_DATE, 'Email', 'ЦБ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000006/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000007, CURRENT_DATE, 07,
        CURRENT_DATE, 'Телефонограмма', 'ЦБ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000007/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000008, date_trunc('day', NOW() - interval '1 month'), 08,
        CURRENT_DATE, 'Телефонограмма', 'ЦБ',
        'Важная тестовая тема документа, кредит.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000008/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000009, date_trunc('day', NOW() - interval '1 month'), 09,
        CURRENT_DATE, 'Email', 'ТСЖ',
        'Важная тестовая тема документа, кредит.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000009/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000010, date_trunc('day', NOW() - interval '1 month'), 10,
        CURRENT_DATE, 'Телефонограмма', 'ТСЖ',
        'Важная тестовая тема документа, кредит.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000010/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000011, date_trunc('day', NOW() - interval '2 month'), 11,
        CURRENT_DATE, 'Телефонограмма', 'ТСЖ',
        'Важная тестовая тема документа, кредит.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000011/test.pdf');
