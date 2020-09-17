package fetch.rewards.email;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class EmailService {

    static final char dot = '.';
    static final char plus = '+';
    static final char ampersand = '@';

    public long unique(final Optional<Set<String>> emails){
        if(!emails.isPresent() || CollectionUtils.isEmpty(emails.get())) return 0;

        return emails.get().stream().filter(email -> StringUtils.isNotBlank(email))
                .filter(email -> email.charAt(0) != ampersand)
                .filter(email -> email.charAt(0) != plus)
                .filter(email -> email.charAt(0) != dot)
                .map(this::removeUnwantedChars).distinct().count();
    }

    public String removeUnwantedChars(final String email){
        final StringBuilder builder = new StringBuilder();
        final char[] chars = email.toCharArray();
        final int posAt = email.indexOf('@');

        for(int i=0; i<chars.length; i++){
            final char aChar = chars[i];

            if(aChar == plus) i = posAt-1;
            else if(aChar != dot && i<posAt) builder.append(aChar);
            else if(i >= posAt) builder.append(aChar);
        }

        return builder.toString();
    }
}
