#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")
package ${PACKAGE_NAME};#end

#if (${PROJECT_NAME} == "USACO")
#parse("File Header.java")

public class ${NAME} {

#parse("USACO Template.java")

}
#else

public class ${NAME} {

}
#end


