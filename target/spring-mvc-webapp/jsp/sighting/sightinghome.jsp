<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superbeing Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">        
    </head>
    <body>
        <div class="pt-4 pb-4 bg-info row">
            <div class="col-md-1"></div>
            <div class="pl-4 col-md-2 text-white">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <h5>Hello, ${pageContext.request.userPrincipal.name}</h5>
                </c:if>
            </div>
            <div class="col-md-6"><h1 class="text-center text-white">Superbeing Sightings</h1></div>
            <div class="col-md-3 row">

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="col-md-4" href="${pageContext.request.contextPath}/user/displayUserList"><button type="button" class="btn btn-default">
                                User Admin</button>
                        </a>                 
                    </sec:authorize>

                <h4 class="col-md-3"><a href="${pageContext.request.contextPath}/login"><button type="button" class="btn btn-default">Login</button></a></h4>
                <h4 class="col-md-3"><a href="<c:url value="/j_spring_security_logout" />" ><button type="button" class="btn btn-default">Logout</button></a></h4>
            </div>
        </div>

        <div class="container pt-4">
            <div class="row">
                <div class="col-xs-2 pr-1">
                    <a href="${pageContext.request.contextPath}/"><button type="button" class="btn btn-sm btn-default">Home</button></a>
                </div>
                <div class="col-xs-2 pr-1">
                    <a href="${pageContext.request.contextPath}/superbeing/superbeinghomePage"><button type="button" class="btn btn-sm btn-default">Superbeings</button></a>
                </div>
                <div class="col-xs-2 pr-1">
                    <a href="${pageContext.request.contextPath}/location/locationhome"><button type="button" class="btn btn-sm btn-default">Locations</button></a>
                </div>
                <div class="col-xs-2 pr-1">
                    <a href="${pageContext.request.contextPath}/organization/organizationhome"><button type="button" class="btn btn-sm btn-default">Organizations</button></a>
                </div>
                <div class="col-xs-2 pr-1">
                    <a href="${pageContext.request.contextPath}/sighting/sightinghome"><button type="button" class="btn btn-sm btn-default">Sightings</button></a>
                </div>
                <div class="col-md-6 pr-1">
                    <form class="row form-group form-group-sm" action="${pageContext.request.contextPath}/search" method="GET">

                        <div class="col-md-6 pr-1">
                            <select required class="form-control form-control-sm" id="searchCategory" name="searchCategory">
                                <option value="" disabled selected hidden>Select Category</option>
                                <option value="Superbeing">Superbeing</option>
                                <option value="Location">Location</option>
                                <option value="Organization">Organization</option>
                            </select>
                        </div>
                        <div class="col-md-4 pr-1">
                            <input type="text" id="searchTerm"  name="searchTerm" class="form-control form-control-sm" placeholder="Search Term" required="required">
                        </div>
                        <div class="col-md-2 pr-1">
                            <button class="btn btn-sm btn-default" type="submit">Search</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="text-center pb-2 row">
                <div class="col-md-2">
                    <a href="${pageContext.request.contextPath}/sighting/addsighting"><button class="btn btn-default">Add Sighting</button></a>
                </div>
                <div class="col-md-10">
                    <form action="searchDate" class="form-group row" method="GET" modelAttribute="Date">
                        <input type="date" name="sightingDate" class="form-control col-md-4"/>
                        <div class="col-sm-2"></div>
                        <button class="btn btn-default col-md-4" type="submit">Search by Date</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="container">
            <div class=""> ${errorMessage}
                <table class="table table-hover">
                    <tr>
                        <th colspan="5" class="text-center bg-info text-white">Sighting List</th>
                    </tr>
                    <tr class="bg-info text-white">
                        <th>
                            Sighting Id No.
                        </th>
                        <th>
                            Superbeing Name
                        </th>
                        <th>
                            Location
                        </th>
                        <th>
                            Date
                        </th>
                        <th/>
                    </tr>
                    <c:forEach var="currentSighting" items="${sightings}"> 
                        <tr>
                            <td class="border-bottom border-info"><c:out value="${currentSighting.sightingId}"/></td>
                            <td class="border-bottom border-info"><c:out value="${currentSighting.superbeingName}"/></td>
                            <td class="border-bottom border-info"><a href="${pageContext.request.contextPath}/location/locationdetails?locationId=${currentSighting.locationId}"><c:out value="${currentSighting.locationName}"/></td></a>
                            <td class="border-bottom border-info"><a href="${pageContext.request.contextPath}/sighting/searchDate?sightingDate=${currentSighting.sightingDate}"><fmt:parseDate value="${currentSighting.sightingDate}" pattern="yyyy-MM-dd" var="newDate"/>
                                    <fmt:formatDate value="${newDate}" pattern="d MMM yyyy" var="newNewDate"/>
                                    <c:out value="${newNewDate}"/></a></td>
                            <td class="border-bottom border-info"><a href="${pageContext.request.contextPath}/sighting/editsighting?sightingId=${currentSighting.sightingId}">Edit</a> | 
                                <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="${pageContext.request.contextPath}/sighting/deleteSighting?sightingId=${currentSighting.sightingId}">Delete</a></sec:authorize>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

    </body>
</html>
