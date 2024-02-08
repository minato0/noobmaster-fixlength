package noobmaster.fixlength.adapter;

import java.lang.reflect.Field;

/**
 * A functional interface about consumer for creating {@link FixLengthAdapter} from {@link Field}.
 *
 * @author Noob Master
 * @see FixLengthAdapter
 * @see Field
 */
@FunctionalInterface
interface FixLengthDefaultAdapterConsumer {

    /**
     * Create {@link FixLengthAdapter} from {@link Field}.
     *
     * @param field class's field
     * @return adapter object
     */
    FixLengthAdapter accept(Field field);
}
