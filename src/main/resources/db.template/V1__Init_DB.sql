create extension if not exists pgcrypto;
update user_account set password=crypt(password, gen_salt('bf',8))
