/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:50:01
 * @modify date 2022-06-03 15:50:01
 * @desc [description]
 */

package com.cbs.exceptions;

public class TargetNotValidException extends IllegalStateException {

    public TargetNotValidException(String target) {
        super(String.format("Target %s not supported. Use either local or gird", target));
    }

}
