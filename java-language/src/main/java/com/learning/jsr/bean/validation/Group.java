package com.learning.jsr.bean.validation;

import javax.validation.groups.Default;
import javax.validation.GroupSequence;

@GroupSequence({Default.class, GroupA.class, GroupB.class})
public interface Group {

}
