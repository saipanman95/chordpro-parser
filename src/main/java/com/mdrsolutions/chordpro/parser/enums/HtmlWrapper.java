package com.mdrsolutions.chordpro.parser.enums;

import com.amazonaws.util.CollectionUtils;
import java.util.Set;

/**
 * Some differing types of HTML wrappers used for wrapping a directive or chord
 * for differing display options.
 *
 * @author michaelrodgers
 */
public enum HtmlWrapper {
    div("<div #>","</div>"), 
    span("<span #>","</span>"), 
    p("<p #>","</p>"), 
    object("<object #>","</object>"), 
    svg("<svg #>","</svg>"), 
    h1("<h1>","</h1>"), 
    h2("<h2>","</h2>"),
    h3("<h3>","</h3>"), 
    h4("<h4>","</h4>"),
    a("<a #>","</a>"),
    html("<html # lang='en-US'>","</html>"),
    head("<head #>","</head>"),
    title("<title #>","</title>"),
    meta("<meta charset='UTF-8'>",""),
    body("<body #>","</body>"),
    table("<table #>","</table>"),
    thead("<thead #>","</thead>"),
    th("<th #>","</th>"),
    tr("<tr #>","</tr>"),
    td("<td #>","</td>"),
    tbody("<tbody #>","</tbody>"),
    pre("<pre>","</pre>"),
    style("<style>","</style>");
    
    private final String start;
    private final String end;

    private HtmlWrapper(String start, String end) {
        this.start = start;
        this.end = end;
    }
    
    public String wrap(String content, Set<HtmlAttribute> attrs){
        if(!CollectionUtils.isNullOrEmpty(attrs)){
            return this.start.replace("#", formatAttr(attrs)) + content + this.end;
        }
        return this.start.replace("#", "") + content + this.end;
    }
    
    public static String wrap(HtmlWrapper html, String content, Set<HtmlAttribute> attrs){
        if(!CollectionUtils.isNullOrEmpty(attrs)){
            return html.start.replace("#", formatAttr(attrs)) + content + html.end;
        }
        return html.start + content + html.end;
    }
        
    
    private static String formatAttr(Set<HtmlAttribute> attributes){
        StringBuilder attrSb = new StringBuilder();
        for(HtmlAttribute attr : attributes){
            attrSb.append(attr.getName()).append("\'").append(attr.getKeyValuePair()).append("\'; ");
        }
        return attrSb.toString();
    }
}

