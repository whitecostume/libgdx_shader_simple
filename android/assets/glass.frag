#ifdef GL_ES
precision mediump float;
#endif
varying vec2 v_texCoords;
varying vec4 v_color;
uniform float widthStep;
uniform float heightStep;
uniform float blurRadiusScale;
uniform sampler2D u_texture;
const float blurRadius = 3.0;
const float blurPixels = (blurRadius * 2.0 + 1.0) * (blurRadius * 2.0 + 1.0);
float random(vec3 scale, float seed) {
    return fract(sin(dot(gl_FragCoord.xyz + seed, scale)) * 43758.5453 + seed);
}
void main()
{
    vec3 sumColor = vec3(0.0, 0.0, 0.0);
     vec4 v = texture2D(u_texture, v_texCoords);
    for(float fy = -blurRadius; fy <= blurRadius; ++fy)
    {
        float dir = random(vec3(12.9898, 78.233, 151.7182), 0.0);
        for(float fx = -blurRadius; fx <= blurRadius; ++fx)
        {
            float dis = distance(vec2(fx * widthStep, fy * heightStep), vec2(0.0, 0.0)) * blurRadiusScale;
            vec2 coord = vec2(dis * cos(dir), dis * sin(dir));
            sumColor += texture2D(u_texture, v_texCoords + coord).rgb;
        }
    }
    gl_FragColor = vec4(sumColor / blurPixels, v.a * v_color.a);
}