
SELECT * FROM ikd_shiro_permission
INSERT INTO dbo.ikd_shiro_permission
    (
        permission_id,
        name,
        description,
        url,
        perms,
        parent_id,
        type,
        order_num,
        icon,
        status,
        create_time,
        update_time
    )
VALUES
    (
        '100004',        -- permission_id - varchar(50)
        '∂©µ•π‹¿Ì',        -- name - varchar(50)
        '',        -- description - varchar(255)
        '',        -- url - varchar(255)
        '',        -- perms - varchar(255)
        null,         -- parent_id - int
        0,         -- type - int
        0,         -- order_num - int
        '&#xe656;',        -- icon - varchar(255)
        1,         -- status - int
        GETDATE(), -- create_time - datetime
        GETDATE()  -- update_time - datetime
    )


	SELECT * FROM dbo.ikd_shiro_role_permission
	INSERT INTO dbo.ikd_shiro_role_permission
	    (
	        role_id,
	        permission_id
	    )
	VALUES
	    (
	        '', -- role_id - varchar(50)
	        ''  -- permission_id - varchar(50)
	    )


